<template>
  <div class="min-h-screen bg-[var(--color-bg)] bg-texture text-[var(--color-fg)] font-body selection:bg-accent-light/30">

    <!-- ==================== SCROLL PROGRESS ==================== -->
    <div ref="progressBar" class="scroll-progress" data-od-id="scroll-progress"></div>

    <!-- ==================== PARTICLE BACKGROUND ==================== -->
    <canvas ref="particleCanvas" class="particle-canvas" data-od-id="particle-bg"></canvas>

    <!-- ==================== NAVIGATION ==================== -->
    <nav
      :class="['fixed top-0 left-0 right-0 z-50 transition-all duration-500', navScrolled ? 'nav-scrolled' : 'nav-transparent']"
      data-od-id="site-nav"
    >
      <div class="content-wrapper flex items-center justify-between h-16 sm:h-20">
        <a href="#hero" class="text-2xl sm:text-3xl font-bold tracking-[0.15em] hover:text-accent transition-colors duration-300" style="font-family: 'Ma Shan Zheng', 'ZCOOL XiaoWei', 'KaiTi', 'STKaiti', serif;" data-od-id="nav-logo">鹿映秦华</a>
        <div class="hidden lg:flex items-center gap-8 text-sm tracking-wider">
          <a v-for="item in navItems" :key="item.id" :href="item.href"
             class="link-underline text-[var(--color-fg)]/70 hover:text-[var(--color-fg)] transition-colors duration-300"
              :data-od-id="'nav-link-' + item.id">{{ item.label }}</a>
        </div>
        <button @click="mobileOpen = !mobileOpen" class="lg:hidden p-2" data-od-id="nav-mobile-toggle">
          <div class="w-5 flex flex-col gap-1.5">
            <span :class="['block h-[1.5px] bg-current transition-all', mobileOpen ? 'rotate-45 translate-y-[5px]' : '']"></span>
            <span :class="['block h-[1.5px] bg-current transition-all', mobileOpen ? 'opacity-0' : '']"></span>
            <span :class="['block h-[1.5px] bg-current transition-all', mobileOpen ? '-rotate-45 -translate-y-[5px]' : '']"></span>
          </div>
        </button>
      </div>
      <!-- mobile menu -->
      <transition name="mobile-menu">
        <div v-if="mobileOpen" class="lg:hidden nav-scrolled border-t border-[var(--color-border)]">
          <div class="content-wrapper flex flex-col gap-4 py-6">
            <a v-for="item in navItems" :key="item.id" :href="item.href"
               @click="mobileOpen = false"
               class="text-lg tracking-wider text-[var(--color-fg)]/70 hover:text-[var(--color-fg)] transition-colors py-2"
                :data-od-id="'mobile-nav-link-' + item.id">{{ item.label }}</a>
          </div>
        </div>
      </transition>
    </nav>

    <!-- ==================== 1. HERO CAROUSEL ==================== -->
    <section id="hero" class="section-container relative h-screen flex items-center" data-od-id="section-hero"
             @mouseenter="stopAutoPlay" @mouseleave="startAutoPlay" @touchstart="stopAutoPlay" @touchend="startAutoPlay">
      <div class="absolute inset-0 overflow-hidden">
        <div ref="heroParallax" class="absolute inset-0 parallax-bg">
          <!-- Carousel slides -->
          <div v-for="(slide, idx) in heroSlides" :key="idx"
               :class="['absolute inset-0 transition-opacity duration-1000 ease-in-out',
                        idx === currentSlide ? 'opacity-100' : 'opacity-0']">
            <img v-if="slide.imageUrl" :src="getImgSrc(slide.imageUrl)" class="absolute inset-0 w-full h-full object-cover" alt="" />
            <div v-else class="ph-img absolute inset-0"></div>
          </div>
          <div class="absolute inset-0 bg-gradient-to-b from-warm-900/25 via-warm-900/12 to-warm-900/35 z-10"></div>
        </div>
      </div>

      <!-- Left/Right navigation buttons -->
      <button @click="prevSlide" aria-label="上一张"
              class="absolute left-3 sm:left-6 z-20 w-10 h-10 sm:w-12 sm:h-12 rounded-full flex items-center justify-center
                     bg-white/10 hover:bg-white/25 backdrop-blur-sm text-white/70 hover:text-white
                     transition-all duration-300 border border-white/20 hover:border-white/40">
        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="15 18 9 12 15 6"/></svg>
      </button>
      <button @click="nextSlide" aria-label="下一张"
              class="absolute right-3 sm:right-6 z-20 w-10 h-10 sm:w-12 sm:h-12 rounded-full flex items-center justify-center
                     bg-white/10 hover:bg-white/25 backdrop-blur-sm text-white/70 hover:text-white
                     transition-all duration-300 border border-white/20 hover:border-white/40">
        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="9 18 15 12 9 6"/></svg>
      </button>

      <!-- Text overlay - 标题与轮播图一一绑定，平滑过渡 -->
      <div class="content-wrapper relative z-10 w-full flex flex-col items-start justify-center h-full pt-6 sm:pt-10">
        <p ref="heroEyebrowEl" class="hero-slide-text eyebrow mb-4 sm:mb-6 opacity-80 text-white/80"
           data-anim="hero">{{ currentSlideData.subtitle || 'LUYING QINHUA · 2025' }}</p>
        <h1 ref="heroTitleEl" class="hero-slide-text display-xl text-white max-w-4xl"
            data-anim="hero-title">
          {{ currentSlideData.title || '鹿映秦华' }}
        </h1>
        <div ref="heroDescEl" class="hero-slide-text flex flex-col sm:flex-row sm:items-center gap-3 sm:gap-6 mt-6 sm:mt-10 max-w-2xl"
             data-anim="hero-desc">
          <span class="h-[1px] w-12 bg-white/40 hidden sm:block"></span>
          <p class="text-white/70 text-base sm:text-lg leading-relaxed tracking-wide font-light"
             v-html="(currentSlideData.description || '赴甘肃天水秦安 · 乡村振兴社会实践<br/>青年力量赋能乡土新生').replace(/\n/g, '<br/>')"></p>
        </div>
        <div class="absolute bottom-10 left-1/2 -translate-x-1/2 flex flex-col items-center gap-3 scroll-hint-anim fade-up delay-500" data-anim="hero-scroll">
          <span class="text-white/50 text-xs tracking-ultra">向下探索</span>
          <svg width="16" height="24" viewBox="0 0 16 24" fill="none" class="text-white/40">
            <rect x="1" y="1" width="14" height="22" rx="7" stroke="currentColor" stroke-width="1.5"/>
            <circle cx="8" cy="7" r="2" fill="currentColor"/>
          </svg>
        </div>
      </div>

      <!-- Slide indicators (dots) -->
      <div class="absolute bottom-28 sm:bottom-32 left-1/2 -translate-x-1/2 flex gap-2.5 z-20">
        <button v-for="(s, i) in heroSlides" :key="'dot-'+i" @click="goToSlide(i)" :aria-label="'第'+(i+1)+'张'"
                :class="['w-2 h-2 rounded-full transition-all duration-500',
                         i === currentSlide ? 'bg-white w-6' : 'bg-white/40 hover:bg-white/60']"></button>
      </div>
    </section>

    <!-- ==================== 2. TEAM ==================== -->
    <section id="team" class="section-container section-gradient-bg section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-team">
      <div class="content-wrapper">
        <div class="flex flex-col lg:flex-row gap-12 lg:gap-24 items-start">
          <!-- left sticky column -->
          <div class="lg:w-[38%] lg:sticky lg:top-28 lg:self-start space-y-6">
            <!-- 团队圆形 LOGO -->
            <div class="flex justify-center lg:justify-start fade-up" data-anim="team-logo">
              <div class="team-logo-wrap">
                <img v-if="teamInfo.teamLogoUrl" :src="getImgSrc(teamInfo.teamLogoUrl)" class="team-logo-img" alt="鹿映秦华 LOGO" />
                <div v-else class="ph-img rounded-full" style="width:160px;height:160px;"><span class="text-warm-300/40 text-xs">LOGO</span></div>
              </div>
            </div>
            <p class="eyebrow fade-up" data-anim="team-eyebrow">{{ teamInfo.eyebrow }}</p>
            <h2 class="display-lg fade-up delay-100 flex items-center gap-4" data-anim="team-title">
              <span>{{ teamInfo.title }}</span>
              <img src="/q-bixin.png" class="q-float q-team-bixin" alt="鹿少女bixin" />
            </h2>
            <p class="section-subtitle fade-up delay-150" data-anim="team-subtitle">{{ teamInfo.subtitle }}</p>
            <p class="body-lg max-w-md fade-up delay-200" data-anim="team-desc">
              {{ teamInfo.description }}
            </p>

            <!-- 队员分工标签 -->
            <div class="flex flex-wrap gap-2 max-w-sm fade-up delay-250" data-anim="team-roles">
              <span class="micro-tag">队长·统筹</span>
              <span class="micro-tag">影像记录</span>
              <span class="micro-tag">田野调研</span>
              <span class="micro-tag">新媒体运营</span>
              <span class="micro-tag">产品设计</span>
              <span class="micro-tag">数据分析</span>
              <span class="micro-tag">外联对接</span>
              <span class="micro-tag">后勤保障</span>
            </div>

            <!-- 核心数据 -->
            <div class="grid grid-cols-2 gap-6 pt-4 max-w-xs fade-up delay-300" data-anim="team-stats">
              <div v-for="(stat, i) in teamStats" :key="'stat-'+i" class="data-card">
                <span v-if="!stat.isText" ref="countEl" :data-target="stat.value" :data-suffix="stat.suffix || ''" class="font-number text-5xl font-bold text-accent tracking-tight data-number count-number">0</span>
                <span v-else class="font-number text-5xl font-bold text-accent tracking-tight data-number">{{ stat.value }}{{ stat.suffix }}</span>
                <p class="text-xs text-warm-600 mt-1 tracking-wide">{{ stat.label }}</p>
              </div>
            </div>

            <!-- 实践初心 -->
            <div class="pt-2 max-w-xs fade-up delay-400" data-anim="team-motto">
              <p class="text-xs text-warm-600 leading-relaxed italic" v-html="teamInfo.motto">
              </p>
            </div>
          </div>

          <!-- right staggered images -->
          <div class="lg:w-[62%] grid grid-cols-6 gap-4 sm:gap-6 auto-rows-[120px] sm:auto-rows-[160px]">
            <!-- team photo 1: large -->
            <div class="col-span-4 row-span-3 rounded-sm hover-scale cursor-pointer fade-up delay-100 overflow-hidden relative"
                 data-anim="team-img1" @click="openLightbox('team-1', teamPhoto(0).url)"
                 data-od-id="team-photo-main">
              <img v-if="teamPhoto(0).url" :src="getImgSrc(teamPhoto(0).url)" class="absolute inset-0 w-full h-full object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img absolute inset-0"><span class="text-warm-300/60">{{ teamPhoto(0).label || '团队合影 · 秦安田间' }}</span></div>
            </div>
            <!-- team photo 2 -->
            <div class="col-span-2 row-span-2 rounded-sm hover-scale cursor-pointer fade-up delay-200 overflow-hidden relative"
                 data-anim="team-img2" @click="openLightbox('team-2', teamPhoto(1).url)"
                 data-od-id="team-photo-workshop">
              <img v-if="teamPhoto(1).url" :src="getImgSrc(teamPhoto(1).url)" class="absolute inset-0 w-full h-full object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img absolute inset-0"><span class="text-warm-300/60 text-xs">{{ teamPhoto(1).label || '田间调研' }}</span></div>
            </div>
            <!-- team photo 3 -->
            <div class="col-span-3 row-span-2 rounded-sm hover-scale cursor-pointer fade-up delay-300 overflow-hidden relative"
                 data-anim="team-img3" @click="openLightbox('team-3', teamPhoto(2).url)"
                 data-od-id="team-photo-village">
              <img v-if="teamPhoto(2).url" :src="getImgSrc(teamPhoto(2).url)" class="absolute inset-0 w-full h-full object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img absolute inset-0"><span class="text-warm-300/60 text-xs">{{ teamPhoto(2).label || '村落走访' }}</span></div>
            </div>
            <!-- team photo 4 -->
            <div class="col-span-3 row-span-2 rounded-sm hover-scale cursor-pointer fade-up delay-400 overflow-hidden relative"
                 data-anim="team-img4" @click="openLightbox('team-4', teamPhoto(3).url)"
                 data-od-id="team-photo-discuss">
              <img v-if="teamPhoto(3).url" :src="getImgSrc(teamPhoto(3).url)" class="absolute inset-0 w-full h-full object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img absolute inset-0"><span class="text-warm-300/60 text-xs">{{ teamPhoto(3).label || '小组研讨' }}</span></div>
            </div>
          </div>
        </div>

        <!-- 成果条 -->
        <div class="mt-16 sm:mt-20 flex flex-wrap items-center gap-6 sm:gap-12 py-6 border-t border-b border-[var(--color-border)] fade-up" data-anim="team-achievements">
          <div v-for="(ach, i) in teamAchievements" :key="'ach-'+i" class="flex items-center gap-3">
            <span class="font-number text-2xl font-bold text-accent">{{ ach.value }}</span>
            <span class="text-xs text-warm-600 tracking-wide">{{ ach.label }}</span>
          </div>
        </div>

        <!-- 青年愿景 - 左图右文布局 -->
        <div class="mt-20 sm:mt-28 lg:mt-36 flex flex-col lg:flex-row items-center gap-10 lg:gap-20">
          <!-- 左侧图片区域 -->
          <div class="lg:w-[45%] w-full rounded-sm overflow-hidden fade-in-scale" data-anim="mission-img">
            <img v-if="teamInfo.missionImageUrl" :src="getImgSrc(teamInfo.missionImageUrl)" class="w-full aspect-[4/3] object-cover rounded-sm" alt="青年愿景" loading="lazy" />
            <div v-else class="ph-img aspect-[4/3] rounded-sm flex items-center justify-center">
              <span class="text-warm-300/50 text-sm tracking-wider">青年愿景 · 影像纪实</span>
            </div>
          </div>
          <!-- 右侧文字区域 -->
          <div class="lg:w-[55%] space-y-6 fade-up" data-anim="team-mission">
            <p class="eyebrow">青年愿景</p>
            <h3 class="display-md" v-html="teamInfo.missionTitle">
            </h3>
            <p class="body-md max-w-lg">
              {{ teamInfo.missionDesc }}
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- ==================== 3. CULTURE ==================== -->
    <section id="culture" class="section-container bg-[var(--color-surface)] section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-culture">
      <div class="content-wrapper">
        <p class="eyebrow text-center lg:text-left mb-4 fade-up" data-anim="culture-eyebrow">秦安 · 天水</p>
        <p class="section-subtitle text-center lg:text-left mb-16 fade-up delay-100" data-anim="culture-subtitle">八千年文明看天水，这些文化切片是秦安最深沉的力量</p>

        <!-- culture items: v-for loop -->
        <div v-for="(item, idx) in cultureItems" :key="'culture-'+idx"
             :class="['flex flex-col gap-8 lg:gap-0 items-center mb-24 lg:mb-32', idx === 1 ? 'lg:flex-row-reverse' : 'lg:flex-row']">
          <div :class="['lg:w-[55%] rounded-sm hover-scale cursor-pointer fade-in-scale overflow-hidden relative']"
               :data-anim="'culture-img'+(idx+1)"
               @click="openLightbox('culture-'+(idx+1), item.imageUrl)"
               :data-od-id="'culture-img-'+idx">
            <img v-if="item.imageUrl" :src="getImgSrc(item.imageUrl)" class="w-full aspect-[4/3] object-cover rounded-sm" alt="" loading="lazy" />
            <div v-else class="ph-img aspect-[4/3]"><span class="text-warm-300/60">{{ item.imagePlaceholder }}</span></div>
          </div>
          <div :class="['lg:w-[45%] z-10 flex flex-col items-start space-y-5 bg-[var(--color-surface)] p-8 sm:p-10 slide-in-right',
                        idx === 1 ? 'lg:-mr-12 lg:mt-20' : 'lg:-ml-12 lg:mt-20']"
               :data-anim="'culture-text'+(idx+1)">
            <div class="flex items-center gap-3">
              <span class="micro-tag text-[10px]">{{ item.subtitleTag }}</span>
              <h3 class="display-md">{{ item.title }}</h3>
            </div>
            <div class="accent-line w-16"></div>
            <p class="body-md">{{ item.description }}</p>
            <div class="flex flex-wrap gap-3 pt-2">
              <span v-for="tag in item.tags" :key="tag.name"
                    class="px-3 py-1.5 border border-[var(--color-border)] rounded-full text-xs tracking-wide text-warm-600 cursor-default culture-tag"
                    :title="tag.note"
                    data-od-id="culture-tag">{{ tag.name }}</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- ==================== 4. PRODUCTS ==================== -->
    <section id="products" class="section-container section-gradient-bg section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-products">
      <div class="content-wrapper">
        <div class="mb-16 sm:mb-20 fade-up" data-anim="prod-header">
          <p class="eyebrow">助农核心</p>
          <h2 class="display-lg mt-4">秦安特色<br /><span class="text-accent">农产品</span></h2>
          <p class="section-subtitle mt-3">黄土高原的馈赠 · 每一口都是秦安大地的诚意</p>
        </div>

        <!-- product 1: 水蜜桃 -->
        <div class="relative mb-32 lg:mb-40">
          <div class="flex flex-col lg:flex-row gap-0">
            <div class="lg:w-[58%] rounded-sm hover-scale cursor-pointer fade-in-scale overflow-hidden relative"
                 data-anim="prod-peach-img"
                 @click="openLightbox('peach', products[0]?.imageUrl)"
                 data-od-id="product-img-peach">
              <img v-if="products[0]?.imageUrl" :src="getImgSrc(products[0].imageUrl)" class="w-full aspect-[4/3] lg:aspect-auto lg:h-[520px] object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img aspect-[4/3] lg:aspect-auto lg:h-[520px]"><span class="text-warm-300/60">{{ products[0]?.imagePlaceholder || '秦安水蜜桃 · 实地拍摄' }}</span></div>
            </div>
            <div class="lg:w-[50%] lg:-ml-16 lg:mt-28 z-10 flex flex-col justify-center space-y-6
                        bg-[var(--color-surface)] p-8 sm:p-12 lg:p-14 shadow-sm slide-in-right delay-200"
                 data-anim="prod-peach-text">
              <span class="eyebrow">{{ products[0]?.subLabel }}</span>
              <h3 class="display-md">
                <span class="text-5xl sm:text-6xl lg:text-7xl font-bold text-accent leading-none block mb-2">{{ products[0]?.namePrefix }}</span>
                {{ products[0]?.name }}
              </h3>
              <div class="accent-line w-20"></div>
              <p class="body-md leading-relaxed">{{ products[0]?.description }}</p>
              <div class="flex flex-wrap gap-2">
                <span v-for="tag in (products[0]?.tags || [])" :key="tag.name" class="micro-tag">{{ tag.name }}</span>
              </div>
              <div class="grid grid-cols-2 gap-4 pt-2">
                <div v-for="(st, si) in (products[0]?.stats || [])" :key="'ps1-'+si" class="data-card">
                  <span v-if="!st.isText" ref="countEl" :data-target="st.value" :data-suffix="st.suffix || ''" class="font-number text-3xl font-bold text-accent tracking-tight data-number count-number">0</span>
                  <span v-else class="font-number text-3xl font-bold text-accent data-number">{{ st.value }}{{ st.suffix }}</span>
                  <span class="text-xs text-warm-600 block mt-1">{{ st.label }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- product 2: 花椒 -->
        <div class="relative">
          <div class="flex flex-col lg:flex-row-reverse gap-0">
            <div class="lg:w-[55%] rounded-sm hover-scale cursor-pointer fade-in-scale overflow-hidden relative"
                 data-anim="prod-pepper-img"
                 @click="openLightbox('pepper', products[1]?.imageUrl)"
                 data-od-id="product-img-pepper">
              <img v-if="products[1]?.imageUrl" :src="getImgSrc(products[1].imageUrl)" class="w-full aspect-[4/3] lg:aspect-auto lg:h-[480px] object-cover rounded-sm" alt="" loading="lazy" />
              <div v-else class="ph-img aspect-[4/3] lg:aspect-auto lg:h-[480px]"><span class="text-warm-300/60">{{ products[1]?.imagePlaceholder || '秦安花椒 · 实地拍摄' }}</span></div>
            </div>
            <div class="lg:w-[52%] lg:-mr-12 lg:mt-24 z-10 flex flex-col justify-center space-y-6
                        bg-[var(--color-surface)] p-8 sm:p-12 lg:p-14 shadow-sm slide-in-right delay-200"
                 data-anim="prod-pepper-text">
              <span class="eyebrow">{{ products[1]?.subLabel }}</span>
              <h3 class="display-md">
                <span class="text-5xl sm:text-6xl lg:text-7xl font-bold text-accent leading-none block mb-2">{{ products[1]?.namePrefix }}</span>
                {{ products[1]?.name }}
              </h3>
              <div class="accent-line w-20"></div>
              <p class="body-md leading-relaxed">{{ products[1]?.description }}</p>
              <div class="flex flex-wrap gap-2">
                <span v-for="tag in (products[1]?.tags || [])" :key="tag.name" class="micro-tag">{{ tag.name }}</span>
              </div>
              <div class="grid grid-cols-2 gap-4 pt-2">
                <div v-for="(st, si) in (products[1]?.stats || [])" :key="'ps2-'+si" class="data-card">
                  <span v-if="!st.isText" ref="countEl" :data-target="st.value" :data-suffix="st.suffix || ''" class="font-number text-3xl font-bold text-accent tracking-tight data-number count-number">0</span>
                  <span v-else class="font-number text-3xl font-bold text-accent data-number">{{ st.value }}{{ st.suffix }}</span>
                  <span class="text-xs text-warm-600 block mt-1">{{ st.label }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- value banner -->
        <div class="mt-24 sm:mt-32 lg:mt-40 text-center lg:text-left fade-up" data-anim="prod-value">
          <div class="flex flex-col lg:flex-row items-center lg:items-start gap-8 lg:gap-20">
            <div class="lg:w-1/2">
              <p class="eyebrow">助农价值</p>
              <h3 class="display-md mt-4">
                不止是产品，<br />
                <span class="text-accent">更是乡村振兴</span><br />
                的希望
              </h3>
            </div>
            <div class="lg:w-1/2">
              <p class="body-md leading-relaxed">
                每一份秦安特色农产品背后，是一个家庭的生计、一个村庄的未来。
                通过品牌赋能、电商助销、青年传播，我们希望让更多人看见秦安、了解秦安、支持秦安。
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- ==================== 5. DOCUMENTARY ==================== -->
    <section id="documentary" class="section-container bg-[var(--color-surface)] section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-documentary">
      <div class="content-wrapper">
        <div class="mb-16 sm:mb-20 fade-up" data-anim="doc-header">
          <p class="eyebrow">实践纪实</p>
          <h2 class="display-lg mt-4">乡村振兴<br /><span class="text-accent">一线手记</span></h2>
          <p class="section-subtitle mt-3">每一帧画面都是真实的秦安故事</p>
        </div>

        <!-- mosaic grid — varied sizes, staggered layout -->
        <div class="doc-mosaic">
          <div v-for="(item, i) in documentaryItems" :key="i"
                :class="['doc-card rounded-sm cursor-pointer doc-img-wrap fade-up', item.cols === 2 ? 'doc-card-wide' : '', item.rows === 2 ? 'doc-card-tall' : '']"
                :style="{ transitionDelay: (i * 0.08) + 's' }"
                :data-anim="'doc-img-' + i"
                @click="openLightbox('doc-' + i, item.imageUrl)"
                :data-od-id="'doc-photo-' + i">
            <img v-if="item.imageUrl" :src="getImgSrc(item.imageUrl)" class="w-full h-full object-cover rounded-sm" alt="" loading="lazy" />
            <div v-else class="doc-card-bg" :style="{ background: item.bg }">
              <span class="doc-card-emoji">{{ item.icon }}</span>
            </div>
            <div class="doc-img-caption">
              <span class="block font-semibold text-white/95 text-xs tracking-wider">{{ item.time }}</span>
              <span class="block text-white/75 mt-0.5 text-[11px]">{{ item.note }}</span>
            </div>
          </div>
        </div>

      </div>
    </section>

    <!-- ==================== 5.5 三大实践价值板块 ==================== -->
    <section id="values" class="section-container section-gradient-bg section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-values">
      <div class="content-wrapper">
        <div class="mb-16 sm:mb-20 fade-up" data-anim="values-header">
          <p class="eyebrow">实践价值</p>
          <h2 class="display-lg mt-4">青春力量<br /><span class="text-accent">赋能乡土</span></h2>
          <p class="section-subtitle mt-3">三个维度，见证青年与乡村的双向奔赴</p>
        </div>

        <!-- 三大板块纵向堆叠 -->
        <div v-for="(story, i) in stories" :key="'value-'+i"
             :class="['flex flex-col gap-8 lg:gap-0 items-center mb-20 lg:mb-28 last:mb-0',
                      i % 2 === 1 ? 'lg:flex-row-reverse' : 'lg:flex-row']">
          <!-- 图片区域 -->
          <div :class="['lg:w-[52%] rounded-sm overflow-hidden fade-in-scale']"
               :data-anim="'value-img-'+i">
            <img v-if="story.imageUrl" :src="getImgSrc(story.imageUrl)" class="w-full aspect-[4/3] object-cover rounded-sm" alt="" loading="lazy" />
            <div v-else class="ph-img aspect-[4/3] rounded-sm flex flex-col items-center justify-center gap-2"
                 :style="{ background: valueCardBg(i) }">
              <span class="text-4xl sm:text-5xl opacity-40">{{ valueCardIcon(i) }}</span>
              <span class="text-warm-300/60 text-xs tracking-wider">{{ story.title }} · 实拍影像</span>
            </div>
          </div>
          <!-- 文字区域 -->
          <div :class="['lg:w-[48%] z-10 flex flex-col items-start space-y-5 bg-[var(--color-surface)] p-8 sm:p-10 lg:p-12 shadow-sm fade-up',
                        i % 2 === 1 ? 'lg:-mr-8 lg:mt-14' : 'lg:-ml-8 lg:mt-14']"
               :data-anim="'value-text-'+i"
               :style="{ transitionDelay: (i * 0.15) + 's' }">
            <!-- Q版IP装饰：01右上 02左上 03右上 -->
            <img v-if="i === 0" src="/q-ok.png" class="q-float q-card-corner q-card-tr" alt="鹿少女ok" />
            <img v-if="i === 1" src="/q-ye.png" class="q-float q-card-corner q-card-tl" alt="鹿少女ye" />
            <img v-if="i === 2" src="/q-dianzan.png" class="q-float q-card-corner q-card-tr" alt="鹿少女dianzan" />
            <span class="font-number text-6xl sm:text-7xl font-bold text-accent/15 block -mb-4 -ml-1 select-none">
              {{ String(i + 1).padStart(2, '0') }}
            </span>
            <h3 class="display-md">{{ story.title }}</h3>
            <div class="accent-line w-16"></div>
            <p class="body-md leading-relaxed">{{ story.desc }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- ==================== MESSAGE INTERACTION ==================== -->
    <section id="message" class="section-container bg-[var(--color-surface)] section-shadow py-24 sm:py-32 lg:py-40" data-od-id="section-message">
      <div class="content-wrapper">
        <div class="mb-16 sm:mb-20 fade-up" data-anim="message-header">
          <p class="eyebrow">互动社区</p>
          <div class="flex items-center gap-4 mt-4">
            <img src="/q-daqi.png" class="q-float q-message-daqi" alt="鹿少女daqi" />
            <h2 class="display-lg">留言<span class="text-accent">互动</span></h2>
          </div>
          <p class="section-subtitle mt-3">留下你的足迹，与我们共同见证秦安的每一步成长</p>
        </div>

        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 lg:gap-20">
          <!-- ====== 留言板（表单） ====== -->
          <div class="fade-up" data-anim="message-board">
            <h3 class="display-md mb-2">📝 留言板</h3>
            <p class="body-sm mb-8 text-warm-500">写下你想说的话，我们珍视每一份声音</p>
            <form @submit.prevent="submitMessage" class="space-y-6">
              <!-- 昵称 -->
              <div>
                <label for="msg-nickname" class="block text-sm text-warm-700 mb-2 tracking-wide">
                  昵称 <span class="text-accent-deep">*</span>
                </label>
                <input id="msg-nickname" v-model="messageForm.nickname" type="text" required
                       placeholder="你的昵称"
                       class="w-full px-4 py-3 bg-[var(--color-bg)] border border-[var(--color-border)] rounded-lg
                              text-warm-800 placeholder-warm-400 text-sm tracking-wide
                              focus:outline-none focus:border-accent focus:ring-1 focus:ring-accent/30
                              transition-all duration-300" />
              </div>
              <!-- 邮箱 -->
              <div>
                <label for="msg-email" class="block text-sm text-warm-700 mb-2 tracking-wide">邮箱</label>
                <input id="msg-email" v-model="messageForm.email" type="email"
                       placeholder="your@email.com（选填）"
                       class="w-full px-4 py-3 bg-[var(--color-bg)] border border-[var(--color-border)] rounded-lg
                              text-warm-800 placeholder-warm-400 text-sm tracking-wide
                              focus:outline-none focus:border-accent focus:ring-1 focus:ring-accent/30
                              transition-all duration-300" />
              </div>
              <!-- 留言内容 -->
              <div>
                <label for="msg-content" class="block text-sm text-warm-700 mb-2 tracking-wide">
                  留言内容 <span class="text-accent-deep">*</span>
                </label>
                <textarea id="msg-content" v-model="messageForm.content" rows="5" required
                          placeholder="分享你的感想..."
                          class="w-full px-4 py-3 bg-[var(--color-bg)] border border-[var(--color-border)] rounded-lg
                                 text-warm-800 placeholder-warm-400 text-sm tracking-wide resize-none
                                 focus:outline-none focus:border-accent focus:ring-1 focus:ring-accent/30
                                 transition-all duration-300"></textarea>
              </div>
              <!-- 提交按钮 -->
              <button type="submit" :disabled="submittingMsg"
                      class="w-full sm:w-auto px-8 py-3 bg-accent text-white rounded-lg text-sm tracking-wider
                             hover:bg-accent-deep disabled:opacity-50 disabled:cursor-not-allowed
                             transition-all duration-300 font-medium">
                {{ submittingMsg ? '提交中...' : '提交留言' }}
              </button>
              <!-- 提示信息 -->
              <p v-if="submitMsgFeedback" :class="['text-xs mt-2', submitMsgOk ? 'text-mist-deep' : 'text-red-400']">
                {{ submitMsgFeedback }}
              </p>
            </form>
          </div>

          <!-- ====== 留言墙（无限滚动） ====== -->
          <div class="fade-up delay-200" data-anim="message-wall">
            <h3 class="display-md mb-2">💬 留言墙</h3>
            <p class="body-sm mb-8 text-warm-500">来自各地朋友的温暖留言</p>
            <div ref="messageWallRef"
                 class="rounded-xl border border-[var(--color-border)] overflow-hidden bg-[var(--color-bg)]"
                 @mouseenter="pauseMessageScroll" @mouseleave="resumeMessageScroll"
                 style="height: 480px;">
              <div ref="messageScrollInner" class="message-wall-scroll py-6 px-5 space-y-4"
                   :class="{ paused: messageScrollPaused }">
                <!-- displayMessages: 三倍列表实现无缝滚动 -->
                <div v-for="(msg, idx) in displayMessages" :key="'dmsg-'+idx"
                     class="message-card p-5 bg-white rounded-lg border border-[var(--color-border)] shadow-sm">
                  <div class="flex items-start justify-between gap-3 mb-2">
                    <div class="flex items-center gap-3">
                      <!-- 头像：有自定义图片则显示图片，否则显示首字母头像 -->
                      <div v-if="msg.avatarUrl" class="msg-avatar-img-wrap">
                        <img :src="getImgSrc(msg.avatarUrl)" class="msg-avatar-img" :alt="msg.nickname" />
                      </div>
                      <div v-else class="msg-avatar-text"
                           :style="{ background: avatarColor(msg.nickname || '匿名') }">
                        {{ (msg.nickname || '匿')[0] }}
                      </div>
                      <span class="font-medium text-sm text-warm-800">{{ msg.nickname || '匿名用户' }}</span>
                    </div>
                    <span class="text-xs text-warm-400 whitespace-nowrap">{{ formatMsgTime(msg.createdAt) }}</span>
                  </div>
                  <p class="text-sm text-warm-600 leading-relaxed">{{ msg.content }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- ==================== CLOSING / FOOTER ==================== -->
    <section id="closing" class="section-container relative py-24 sm:py-32 lg:py-36 overflow-hidden" data-od-id="section-closing">
      <div class="absolute inset-0 overflow-hidden">
        <div class="absolute inset-0">
          <img v-if="closingBgImage" :src="getImgSrc(closingBgImage)" class="absolute inset-0 w-full h-full object-cover" alt="" />
          <div v-else class="ph-img absolute inset-0"><span class="text-warm-300/40">秦安大地 · 航拍全景</span></div>
        </div>
        <div class="absolute inset-0 bg-warm-900/35 backdrop-blur-[2px]"></div>
        <div class="absolute inset-0 closing-glow"></div>
      </div>
      <div class="content-wrapper relative z-10">
        <div class="flex flex-col lg:flex-row items-start justify-between gap-12 lg:gap-16">
          <!-- 左侧文字组：向左上角偏移 -->
          <div class="flex flex-col items-start text-left space-y-4 lg:pt-0 fade-up" data-anim="closing-left">
            <h2 class="display-lg text-white" style="font-family: 'Ma Shan Zheng', 'ZCOOL XiaoWei', 'KaiTi', 'STKaiti', serif;">
              {{ clBrand }}
            </h2>
            <p class="text-white/50 text-sm leading-relaxed tracking-wide max-w-xs">
              {{ clSubtitle }}
            </p>
            <img src="/q-gaoju.png" class="q-float q-footer-gaoju" alt="鹿少女gaoju" />
            <div class="flex flex-wrap gap-4 sm:gap-6 pt-4 text-white/40 text-xs tracking-wide">
              <button @click="sharePage" class="hover:text-accent-light transition-colors duration-300 link-underline" data-od-id="footer-share">分享本站</button>
              <a href="mailto:luyingqinhua@example.com" class="hover:text-accent-light transition-colors duration-300 link-underline" data-od-id="footer-email">联系我们</a>
              <a href="#hero" class="hover:text-accent-light transition-colors duration-300 link-underline" data-od-id="footer-top">返回顶部</a>
            </div>
          </div>

          <!-- 右侧：媒体平台图标区域 - 2x2 统一尺寸纯图片展示 -->
          <div class="flex flex-col items-end gap-4 lg:pt-0 fade-up delay-200" data-anim="closing-right">
            <span class="text-white/30 text-xs tracking-ultra uppercase">关注我们</span>
            <div class="footer-icon-grid">
              <div class="footer-icon-cell" title="小红书">
                <img src="/footer-icon-1.png" class="footer-icon-img" alt="小红书" />
              </div>
              <div class="footer-icon-cell" title="视频号">
                <img src="/footer-icon-2.png" class="footer-icon-img" alt="视频号" />
              </div>
              <div class="footer-icon-cell" title="快手">
                <img src="/footer-icon-3.png" class="footer-icon-img" alt="快手" />
              </div>
              <div class="footer-icon-cell" title="抖音">
                <img src="/footer-icon-4.png" class="footer-icon-img" alt="抖音" />
              </div>
            </div>
          </div>
        </div>

        <!-- 底部版权条 -->
        <div class="mt-12 sm:mt-16">
          <div class="h-[1px] w-full bg-white/10 mb-5"></div>
          <p class="text-white/25 text-xs tracking-wide text-center lg:text-left">
            &copy; 2026 鹿映秦华实践队 · 青年助力乡村振兴
          </p>
        </div>
      </div>
    </section>

    <!-- ==================== LIGHTBOX ==================== -->
    <Teleport to="body">
      <Transition name="lightbox">
        <div v-if="lightboxOpen"
             class="fixed inset-0 z-[100] bg-warm-900/95 flex items-center justify-center cursor-pointer"
             @click="closeLightbox"
             @wheel.prevent
             @touchmove.prevent
             data-od-id="lightbox">
          <button @click.stop="closeLightbox"
                  class="absolute top-6 right-6 sm:top-8 sm:right-8 text-white/60 hover:text-white transition-colors z-10"
                  data-od-id="lightbox-close">
            <svg width="32" height="32" viewBox="0 0 32 32" fill="none" stroke="currentColor" stroke-width="1.5">
              <line x1="8" y1="8" x2="24" y2="24"/><line x1="24" y1="8" x2="8" y2="24"/>
            </svg>
          </button>
          <div class="w-[90vw] h-[70vh] max-w-5xl rounded-sm" @click.stop>
            <img v-if="lightboxImageUrl" :src="getImgSrc(lightboxImageUrl)" class="w-full h-full object-contain rounded-sm" alt="" />
            <div v-else class="ph-img w-full h-full"><span class="text-warm-300/50">图片预览</span></div>
          </div>
          <p class="absolute bottom-8 left-1/2 -translate-x-1/2 text-white/40 text-xs tracking-wide">
            点击任意处关闭
          </p>
        </div>
      </Transition>
    </Teleport>

  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted, onBeforeUnmount, nextTick } from 'vue'
import { getImgSrc, getThumbSrc } from './utils/imgUrl.js'

const navScrolled = ref(false)
const mobileOpen = ref(false)
const lightboxOpen = ref(false)
const lightboxId = ref('')
const lightboxImageUrl = ref('')
const heroParallax = ref(null)
const heroEyebrowEl = ref(null)
const heroTitleEl = ref(null)
const heroDescEl = ref(null)
const progressBar = ref(null)
const particleCanvas = ref(null)

// ====== Hero Carousel ======
const currentSlide = ref(0)
let autoPlayTimer = null

const currentSlideData = computed(() => {
  const slides = heroSlides.value
  if (slides && slides.length > 0) {
    return slides[currentSlide.value] || slides[0]
  }
  return { title: '鹿映秦华', subtitle: 'LUYING QINHUA · 2025', description: '赴甘肃天水秦安 · 乡村振兴社会实践\n青年力量赋能乡土新生' }
})

const heroSlides = computed(() => {
  if (pageData.value?.heroSlides && pageData.value.heroSlides.length > 0) {
    return pageData.value.heroSlides.filter(s => s.enabled !== false)
  }
  // 兜底：单张默认轮播
  return [{ imageUrl: '', title: '鹿映秦华', subtitle: 'LUYING QINHUA · 2025', description: '赴甘肃天水秦安 · 乡村振兴社会实践\n青年力量赋能乡土新生', enabled: true }]
})

function nextSlide() {
  currentSlide.value = (currentSlide.value + 1) % heroSlides.value.length
}
function prevSlide() {
  currentSlide.value = (currentSlide.value - 1 + heroSlides.value.length) % heroSlides.value.length
}
function goToSlide(idx) {
  currentSlide.value = idx
  resetAutoPlay()
}
function startAutoPlay() {
  stopAutoPlay()
  autoPlayTimer = setInterval(nextSlide, 5000)
}
function stopAutoPlay() {
  if (autoPlayTimer) { clearInterval(autoPlayTimer); autoPlayTimer = null }
}
function resetAutoPlay() {
  stopAutoPlay()
  startAutoPlay()
}

// Hero 文字切换过渡：每次切片时先闪隐再闪现
watch(currentSlide, () => {
  const els = [heroEyebrowEl.value, heroTitleEl.value, heroDescEl.value].filter(Boolean)
  if (!els.length) return
  els.forEach(el => {
    el.classList.remove('hero-text-visible')
    el.style.opacity = '0'
    el.style.transform = 'translateY(12px)'
  })
  setTimeout(() => {
    els.forEach(el => {
      el.style.opacity = '1'
      el.style.transform = 'translateY(0)'
      el.classList.add('hero-text-visible')
    })
  }, 150)
}, { flush: 'post' })

// 页脚媒体图标（2x2 纯图片展示）
const footerIcons = computed(() => {
  const icons = pageData.value?.footerIcons
  if (icons && icons.length > 0) {
    const enabled = icons.filter(i => i.enabled !== false)
    if (enabled.length >= 4) return enabled.slice(0, 4)
  }
  // 兜底占位
  return [
    { id: 1, name: '小红书', imageUrl: '', enabled: true },
    { id: 2, name: '视频号', imageUrl: '', enabled: true },
    { id: 3, name: '快手', imageUrl: '', enabled: true },
    { id: 4, name: '抖音', imageUrl: '', enabled: true },
  ]
})

// ====== 留言互动 ======
const messageForm = ref({ nickname: '', email: '', content: '' })
const submittingMsg = ref(false)
const submitMsgFeedback = ref('')
const submitMsgOk = ref(false)
const messages = ref([])
const messageWallRef = ref(null)
const messageScrollInner = ref(null)
const messageScrollPaused = ref(false)

// 最小6条（仅展示 enabled 的留言），不足则补齐
const paddedMessages = computed(() => {
  const list = (messages.value || []).filter(m => m.enabled !== false)
  if (list.length === 0) return []
  if (list.length >= 6) return list
  const result = [...list]
  while (result.length < 6) {
    for (let i = 0; i < list.length && result.length < 6; i++) {
      result.push(list[i])
    }
  }
  return result.slice(0, 6)
})

// 三倍复制实现无缝无限滚动
const displayMessages = computed(() => {
  const base = paddedMessages.value
  if (!base || base.length === 0) return []
  return [...base, ...base, ...base]
})

// 头像背景色
function avatarColor(name) {
  const colors = ['#d4c4a8', '#b8cfc9', '#e8d5c4', '#c8d6cf', '#d4c8b8', '#c0d4c8']
  let hash = 0
  for (let i = 0; i < name.length; i++) hash = name.charCodeAt(i) + ((hash << 5) - hash)
  return colors[Math.abs(hash) % colors.length]
}

// 格式化留言时间
function formatMsgTime(t) {
  if (!t) return ''
  const d = new Date(t)
  const now = new Date()
  const diff = now - d
  if (diff < 60000) return '刚刚'
  if (diff < 3600000) return Math.floor(diff / 60000) + '分钟前'
  if (diff < 86400000) return Math.floor(diff / 3600000) + '小时前'
  if (diff < 604800000) return Math.floor(diff / 86400000) + '天前'
  return d.toLocaleDateString('zh-CN', { month: 'short', day: 'numeric' })
}

function pauseMessageScroll() { messageScrollPaused.value = true }
function resumeMessageScroll() { messageScrollPaused.value = false }

// 提交留言
async function submitMessage() {
  if (!messageForm.value.nickname.trim() || !messageForm.value.content.trim()) return
  submittingMsg.value = true
  submitMsgFeedback.value = ''
  try {
    const res = await fetch(`${API_BASE}/api/messages`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        nickname: messageForm.value.nickname.trim(),
        email: messageForm.value.email.trim(),
        content: messageForm.value.content.trim(),
      }),
    })
    const json = await res.json()
    if (json.code === 200) {
      submitMsgFeedback.value = '✅ 留言提交成功！感谢你的参与。'
      submitMsgOk.value = true
      messageForm.value = { nickname: '', email: '', content: '' }
      // 刷新留言墙
      await fetchMessages()
    } else {
      submitMsgFeedback.value = json.message || '提交失败，请稍后重试'
      submitMsgOk.value = false
    }
  } catch (e) {
    submitMsgFeedback.value = '网络异常，请稍后重试'
    submitMsgOk.value = false
  } finally {
    submittingMsg.value = false
    // 3秒后清除反馈
    setTimeout(() => { submitMsgFeedback.value = '' }, 5000)
  }
}

// 获取留言列表
async function fetchMessages() {
  try {
    const res = await fetch(`${API_BASE}/api/messages`)
    const json = await res.json()
    if (json.code === 200 && json.data && json.data.length > 0) {
      messages.value = json.data
    } else {
      messages.value = defaults.seedMessages
    }
  } catch (e) {
    console.warn('获取留言失败，使用种子数据:', e.message)
    messages.value = defaults.seedMessages
  }
}
const API_BASE = import.meta.env.VITE_API_BASE_URL ?? ''
const loading = ref(true)
const pageData = ref(null)

// ====== 默认数据（API 获取失败时兜底） ======
const defaults = {
  navItems: [
    { id: 'team', label: '团队', href: '#team' },
    { id: 'culture', label: '秦安文化', href: '#culture' },
    { id: 'products', label: '农产品', href: '#products' },
    { id: 'documentary', label: '实践纪实', href: '#documentary' },
    { id: 'message', label: '留言互动', href: '#message' },
    { id: 'closing', label: '关于', href: '#closing' },
  ],
  teamInfo: {
    eyebrow: '团队概览', title: '我们是谁',
    subtitle: '一群想把论文写在祖国大地上的年轻人',
    description: '鹿映秦华实践队由来自多所高校的青年学子组成，聚焦甘肃秦安乡村振兴一线，以影像记录、田野调研、产品助销为核心，用青年视角讲述乡土故事。',
    missionTitle: '以镜头为笔，<br />以土地为卷，<br />书写乡村的当代叙事。',
    missionDesc: '我们相信，乡村振兴不只是基建与产业，更是文化与记忆的延续。鹿映秦华以青年视角重新发现乡土价值，让每寸土地的故事被看见。',
    missionImageUrl: '',
    motto: '" 用镜头记录变迁，用脚步丈量土地，用青春回应时代。"',
  },
  teamStats: [
    { label: '核心队员', value: '12', suffix: '', isText: false },
    { label: '实践天数', value: '30', suffix: '', isText: false },
    { label: '调研村落', value: '5', suffix: '', isText: false },
    { label: '纪实影像', value: '2K+', suffix: '', isText: true },
  ],
  teamAchievements: [
    { value: '12', label: '篇调研报告' },
    { value: '8', label: '场助农直播' },
    { value: '50w+', label: '传播曝光' },
    { value: '3', label: '项合作意向' },
  ],
  cultureItems: [
    { subtitleTag: '文脉', title: '陇右文脉', description: '天水古称秦州，是华夏文明的重要发源地之一。', imagePlaceholder: '麦积山石窟 · 世界文化遗产',
      tags: [{ name: '大地湾遗址', note: '八千年文明曙光' }, { name: '伏羲文化', note: '人文始祖故里' }, { name: '麦积山石窟', note: '东方雕塑馆' }, { name: '陇右古道', note: '丝路咽喉要冲' }] },
    { subtitleTag: '非遗', title: '非遗民俗', description: '秦安小曲悠扬婉转，秦安蜡花舞古朴动人。', imagePlaceholder: '秦安非遗 · 民俗文化纪实',
      tags: [{ name: '秦安小曲', note: '国家级非遗曲艺' }, { name: '蜡花舞', note: '千年祭祀舞蹈' }, { name: '草编工艺', note: '指尖上的传统' }, { name: '社火民俗', note: '黄土高原年味' }] },
    { subtitleTag: '地貌', title: '乡土风貌', description: '秦安地处陇中黄土高原腹地，葫芦河穿城而过。', imagePlaceholder: '秦安梯田 · 黄土高原乡土风貌',
      tags: [{ name: '黄土梯田', note: '大地的等高线' }, { name: '葫芦河', note: '秦安母亲河' }, { name: '古村落', note: '明清建筑遗存' }, { name: '陇中地貌', note: '千沟万壑奇观' }] },
  ],
  products: [
    { subLabel: '核心产品 01', namePrefix: '秦安', name: '水蜜桃', imagePlaceholder: '秦安水蜜桃 · 实地拍摄',
      description: '秦安水蜜桃因黄土高原独特的气候条件，光照充足、昼夜温差大，孕育出果肉细嫩、汁多味甜的极品蜜桃。',
      tags: [{ name: '🍑 果肉细嫩' }, { name: '💧 汁多味甜' }, { name: '🎁 送礼佳品' }, { name: '🏠 家庭鲜享' }],
      stats: [{ label: '年均日照', value: '1800', suffix: 'h+', isText: false }, { label: '昼夜温差', value: '15', suffix: '°C', isText: false }, { label: '种植面积', value: '30', suffix: '万亩', isText: false }, { label: '地理标志', value: '国家地标', suffix: '', isText: true }] },
    { subLabel: '核心产品 02', namePrefix: '秦安', name: '花椒', imagePlaceholder: '秦安花椒 · 实地拍摄',
      description: '秦安花椒色泽鲜红、颗粒饱满、麻香浓郁，是国家地理标志保护产品。',
      tags: [{ name: '🌶 麻香浓郁' }, { name: '✨ 颗粒饱满' }, { name: '🍲 川味必备' }, { name: '🏭 食品加工' }],
      stats: [{ label: '特有品种', value: '大红袍', suffix: '', isText: true }, { label: '种植面积', value: '16', suffix: '万亩', isText: false }, { label: '品质认证', value: '国家地标', suffix: '', isText: true }, { label: '从业农户', value: '2', suffix: '万+', isText: false }] },
  ],
  documentaryItems: [
    { label: '队员下乡走访农户', cols: 1, rows: 1, time: '7月12日 · 刘坪镇', note: '深入桃园与果农交流', bg: 'linear-gradient(135deg, #e8d5c4 0%, #d4a88c 40%, #c49a76 100%)', icon: '🌳' },
    { label: '田间地头实地调研', cols: 2, rows: 1, time: '7月13日 · 兴国镇', note: '花椒种植基地采样', bg: 'linear-gradient(160deg, #c8d6cf 0%, #8aaa9a 50%, #6b9980 100%)', icon: '🌾' },
    { label: '与村民深入交流', cols: 1, rows: 1, time: '7月14日 · 陇城镇', note: '入户访谈记录乡音', bg: 'linear-gradient(145deg, #d4c8b8 0%, #baa890 35%, #9e8b73 100%)', icon: '🏘️' },
    { label: '合作社参观学习', cols: 1, rows: 2, time: '7月15日 · 郭嘉镇', note: '了解集体经济发展', bg: 'linear-gradient(150deg, #b8c8d0 0%, #8da5b0 45%, #6d8a96 100%)', icon: '📋' },
    { label: '志愿服务纪实', cols: 1, rows: 1, time: '7月16日 · 叶堡镇', note: '助农采摘与包装', bg: 'linear-gradient(140deg, #e0d4c0 0%, #c4ad8c 40%, #a88b6a 100%)', icon: '🍑' },
    { label: '乡村振兴座谈会', cols: 1, rows: 1, time: '7月17日 · 县乡村振兴局', note: '青年建言献策', bg: 'linear-gradient(155deg, #c0d4c8 0%, #8ab89a 50%, #6a9a7c 100%)', icon: '🤝' },
    { label: '村小支教瞬间', cols: 1, rows: 1, time: '7月18日 · 莲花镇', note: '为留守儿童带去课堂', bg: 'linear-gradient(130deg, #d8ccc0 0%, #b8a080 38%, #948060 100%)', icon: '📚' },
    { label: '团队每日复盘', cols: 1, rows: 1, time: '7月19日 · 驻地', note: '整理当日调研成果', bg: 'linear-gradient(148deg, #c4d0cc 0%, #8ea898 48%, #6c8880 100%)', icon: '✍️' },
    { label: '丰收时节助农', cols: 2, rows: 1, time: '7月20日 · 王铺镇', note: '直播助销秦安蜜桃', bg: 'linear-gradient(142deg, #e0ccc0 0%, #c4a080 42%, #a88060 100%)', icon: '📦' },
  ],
  stories: [
    { title: '走进田间地头', desc: '队员们深入秦安多个村庄，走进果园、椒田，与农户面对面交流，了解产业发展现状与真实需求。', imageUrl: '' },
    { title: '记录乡土变迁', desc: '用镜头记录秦安传统村落的新旧更迭，见证乡村振兴战略给这片土地带来的深刻变化。', imageUrl: '' },
    { title: '青春赋能乡土', desc: '发挥青年学子的专业优势，为秦安农产品品牌化、电商化提供方案建议与技术支撑。', imageUrl: '' },
  ],
  // 种子留言数据（后端不可用时的兜底数据）
  seedMessages: [
    { nickname: '晓风残月', email: '', content: '乡村振兴需要更多年轻人的参与和关注，鹿映秦华加油！希望秦安的蜜桃能走向全国。', avatarUrl: '', enabled: true, createdAt: '2025-07-15T10:30:00' },
    { nickname: '田野守望者', email: '', content: '我是秦安本地人，看到你们年轻人为家乡宣传，真的很感动。欢迎大家来秦安做客！', avatarUrl: '', enabled: true, createdAt: '2025-07-16T14:20:00' },
    { nickname: '城市旅人', email: '', content: '去过一次秦安，被那里的淳朴和美景深深吸引。期待你们的实践成果展示。', avatarUrl: '', enabled: true, createdAt: '2025-07-17T09:15:00' },
    { nickname: '文化爱好者', email: '', content: '大地湾遗址和麦积山石窟都是华夏文明的瑰宝，感谢你们让更多人了解这里。', avatarUrl: '', enabled: true, createdAt: '2025-07-18T16:45:00' },
    { nickname: '青年志愿者', email: '', content: '同为大学生，你们的实践精神值得学习。把论文写在祖国大地上，说得太好了。', avatarUrl: '', enabled: true, createdAt: '2025-07-19T11:00:00' },
    { nickname: '陇上人家', email: '', content: '黄土高原的儿女永远热爱这片土地。鹿映秦华，名字真好听，寓意深远。', avatarUrl: '', enabled: true, createdAt: '2025-07-20T08:30:00' },
    { nickname: '摄影小兵', email: '', content: '纪录片的视角很棒！期待看到更多关于秦安风土人情的纪实影像。', avatarUrl: '', enabled: true, createdAt: '2025-07-21T13:00:00' },
    { nickname: '热心网友', email: '', content: '网站做得很有格调，简约大气。祝愿秦安越来越好！', avatarUrl: '', enabled: true, createdAt: '2025-07-22T10:10:00' },
    { nickname: '麦积烟雨', email: '', content: '天水麦积山的石窟艺术让人叹为观止，秦安小曲婉转悠扬，文化底蕴太深厚了。', avatarUrl: '', enabled: true, createdAt: '2025-07-23T15:00:00' },
    { nickname: '蜜桃甜甜', email: '', content: '秦安水蜜桃真的太好吃了！汁多味甜，每年夏天都盼着这一口。', avatarUrl: '', enabled: true, createdAt: '2025-07-24T09:30:00' },
    { nickname: '行走陇上', email: '', content: '从黄土高原到葫芦河畔，秦安的每一寸土地都充满了故事。致敬每一位乡村振兴的奋斗者。', avatarUrl: '', enabled: true, createdAt: '2025-07-25T11:00:00' },
    { nickname: '归来少年', email: '', content: '毕业后回到家乡秦安工作已经三年了，看到越来越多的人关注这里，特别欣慰。', avatarUrl: '', enabled: true, createdAt: '2025-07-26T14:30:00' },
  ],
}

// ====== 从 pageData 计算派生数据 ======
const navItems = computed(() => {
  const raw = (pageData.value?.navItems && pageData.value.navItems.length > 0)
    ? [...pageData.value.navItems]
    : [...defaults.navItems]
  // 将后端字段统一映射：优先用 navId（字符串标识），否则用 id
  const items = raw.map(n => ({
    id: n.navId || n.id,
    label: n.label,
    href: n.href,
  }))
  // 确保「留言互动」始终存在于「实践纪实」和「关于」之间
  const hasMessage = items.some(i => i.id === 'message')
  if (!hasMessage) {
    const docIdx = items.findIndex(i => i.id === 'documentary')
    if (docIdx >= 0) {
      items.splice(docIdx + 1, 0, { id: 'message', label: '留言互动', href: '#message' })
    }
  }
  return items
})
const teamInfo = computed(() => pageData.value?.teamInfo || defaults.teamInfo)
const teamStats = computed(() => pageData.value?.teamStats || defaults.teamStats)
const teamAchievements = computed(() => pageData.value?.teamAchievements || defaults.teamAchievements)
const cultureItems = computed(() => pageData.value?.cultureItems || defaults.cultureItems)
const products = computed(() => pageData.value?.products || defaults.products)
const documentaryItems = computed(() => {
  const items = pageData.value?.documentaryItems || defaults.documentaryItems
  return items.map(d => ({
    label: d.label, cols: d.colsSpan ?? d.cols ?? 1, rows: d.rowsSpan ?? d.rows ?? 1,
    time: d.timeLabel ?? d.time ?? '', note: d.note,
    bg: d.bgGradient ?? d.bg ?? '', icon: d.icon ?? '', imageUrl: d.imageUrl ?? '',
  }))
})
const stories = computed(() => {
  const s = pageData.value?.stories || defaults.stories
  return s.map(st => ({ title: st.title, desc: st.description ?? st.desc ?? '', imageUrl: st.imageUrl || '' }))
})
const closingBgImage = computed(() => pageData.value?.closingImage || '')
const clBrand = computed(() => pageData.value?.closingBrand || '鹿映秦华')
const clSubtitle = computed(() => pageData.value?.closingSubtitle || '以青年视角重新发现乡土价值')

// 团队照片（从 teamInfo 获取）
function teamPhoto(idx) {
  const info = teamInfo.value
  if (!info) return { url: '', label: '' }
  const urls = [info.photo1Url, info.photo2Url, info.photo3Url, info.photo4Url]
  const labels = [info.photo1Label, info.photo2Label, info.photo3Label, info.photo4Label]
  return { url: urls[idx] || '', label: labels[idx] || '' }
}

// 三大实践价值板块 - 卡片背景色与图标
function valueCardBg(idx) {
  const gradients = [
    'linear-gradient(135deg, #e8d5c4 0%, #d4b89c 50%, #c4a080 100%)',
    'linear-gradient(160deg, #c8d6cf 0%, #9ab8a8 50%, #7a9e8c 100%)',
    'linear-gradient(145deg, #d4c8b8 0%, #baa890 50%, #9e8b73 100%)',
  ]
  return gradients[idx] || gradients[0]
}
function valueCardIcon(idx) {
  const icons = ['🌾', '📷', '🌟']
  return icons[idx] || '📷'
}

// 图片路径由 src/utils/imgUrl.js 统一处理（自动区分开发/生产环境）


// ---- scroll handlers ----
let ticking = false
function onScroll() {
  if (!ticking) {
    requestAnimationFrame(() => {
      const y = window.scrollY
      navScrolled.value = y > 60

      if (heroParallax.value) {
        heroParallax.value.style.transform = `translateY(${y * 0.35}px)`
      }

      // scroll progress bar
      if (progressBar.value) {
        const docH = document.documentElement.scrollHeight - window.innerHeight
        const pct = docH > 0 ? Math.min(y / docH * 100, 100) : 0
        progressBar.value.style.width = pct + '%'
        progressBar.value.classList.toggle('active', y > 100)
      }

      ticking = false
    })
    ticking = true
  }
}

// ---- intersection observer for animations ----
let observer = null
function setupObserver() {
  observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('visible')
        observer.unobserve(entry.target)

        // trigger counting animation for count-number elements
        const counts = entry.target.querySelectorAll('.count-number[data-target]')
        counts.forEach(el => animateCount(el))
      }
    })
  }, { threshold: 0.15, rootMargin: '0px 0px -40px 0px' })

  nextTick(() => {
    document.querySelectorAll('.fade-up, .fade-in-scale, .slide-in-right, .fade-in-soft').forEach(el => observer.observe(el))
  })
}

// ---- number counting animation ----
function animateCount(el) {
  if (el.dataset.counted) return
  el.dataset.counted = 'true'

  const target = parseFloat(el.dataset.target)
  const suffix = el.dataset.suffix || ''
  const duration = 1600
  const startTime = performance.now()

  function step(now) {
    const elapsed = now - startTime
    const progress = Math.min(elapsed / duration, 1)
    const eased = 1 - Math.pow(1 - progress, 3)
    const current = Math.round(target * eased)
    el.textContent = current + suffix

    if (progress < 1) {
      requestAnimationFrame(step)
    } else {
      el.textContent = target + suffix
    }
  }

  requestAnimationFrame(step)
}

// ---- particle canvas (viewport-only, debounced resize) ----
let particleAnimId = null
let particleResizeTimer = null
function initParticles() {
  const canvas = particleCanvas.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const particles = []
  const maxParticles = 30

  function resize() {
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
  }
  resize()

  for (let i = 0; i < maxParticles; i++) {
    particles.push({
      x: Math.random() * canvas.width,
      y: Math.random() * canvas.height,
      r: Math.random() * 1.5 + 0.5,
      vx: (Math.random() - 0.5) * 0.15,
      vy: (Math.random() - 0.5) * 0.15,
      alpha: Math.random() * 0.3 + 0.05,
    })
  }

  function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)

    for (const p of particles) {
      p.x += p.vx
      p.y += p.vy

      if (p.x < 0) p.x = canvas.width
      if (p.x > canvas.width) p.x = 0
      if (p.y < 0) p.y = canvas.height
      if (p.y > canvas.height) p.y = 0

      ctx.beginPath()
      ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2)
      ctx.fillStyle = `rgba(196, 166, 130, ${p.alpha})`
      ctx.fill()
    }

    particleAnimId = requestAnimationFrame(draw)
  }

  draw()

  const onResize = () => {
    clearTimeout(particleResizeTimer)
    particleResizeTimer = setTimeout(() => {
      const w2 = window.innerWidth
      const h2 = window.innerHeight
      if (canvas.width !== w2 || canvas.height !== h2) {
        resize()
        for (const p of particles) {
          p.x = Math.min(p.x, canvas.width)
          p.y = Math.min(p.y, canvas.height)
        }
      }
    }, 150)
  }

  window.addEventListener('resize', onResize)
  particleCanvas.value._cleanup = () => window.removeEventListener('resize', onResize)
}

// ---- lightbox ----
function openLightbox(id, imageUrl) {
  lightboxId.value = id
  lightboxImageUrl.value = imageUrl || ''
  lightboxOpen.value = true
  document.body.style.overflow = 'hidden'
}

function closeLightbox() {
  lightboxOpen.value = false
  document.body.style.overflow = ''
}

// ---- share ----
function sharePage() {
  if (navigator.share) {
    navigator.share({ title: '鹿映秦华 — 甘肃秦安乡村振兴实践', url: window.location.href })
  } else {
    navigator.clipboard.writeText(window.location.href).then(() => {
      alert('链接已复制，欢迎分享！')
    })
  }
}



// ---- lifecycle ----
onMounted(async () => {
  window.addEventListener('scroll', onScroll, { passive: true })
  // 从后端获取数据
  try {
    const res = await fetch(`${API_BASE}/api/site/full`)
    const json = await res.json()
    if (json.code === 200 && json.data) {
      pageData.value = json.data
    }
  } catch (e) {
    console.warn('后端 API 不可用，使用默认数据:', e.message)
  } finally {
    loading.value = false
  }
  await nextTick()
  setupObserver()
  onScroll()
  initParticles()
  startAutoPlay()
  fetchMessages()
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', onScroll)
  if (observer) observer.disconnect()
  if (particleAnimId) cancelAnimationFrame(particleAnimId)
  if (particleResizeTimer) clearTimeout(particleResizeTimer)
  if (particleCanvas.value && particleCanvas.value._cleanup) particleCanvas.value._cleanup()
  stopAutoPlay()
  document.body.style.overflow = ''
})
</script>

<style scoped>
.mobile-menu-enter-active,
.mobile-menu-leave-active {
  transition: all 0.3s ease;
}
.mobile-menu-enter-from,
.mobile-menu-leave-to {
  opacity: 0;
  max-height: 0;
}
.mobile-menu-enter-to,
.mobile-menu-leave-from {
  opacity: 1;
  max-height: 400px;
}
</style>
