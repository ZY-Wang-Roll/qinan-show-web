package com.luyingqinhua.config;

import com.luyingqinhua.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void run(String... args) {
        // 检查是否已初始化（幂等）
        Long count = (Long) em.createQuery("SELECT COUNT(n) FROM NavItem n").getSingleResult();
        if (count > 0) return;

        seedNavItems();
        seedTeamInfo();
        seedTeamStats();
        seedTeamAchievements();
        seedCultureItems();
        seedProducts();
        seedDocumentary();
        seedStories();

        em.flush();
    }

    private void seedNavItems() {
        String[][] items = {
            {"team", "团队", "#team"},
            {"culture", "秦安文化", "#culture"},
            {"products", "农产品", "#products"},
            {"documentary", "实践纪实", "#documentary"},
            {"closing", "关于", "#closing"},
        };
        for (int i = 0; i < items.length; i++) {
            NavItem n = new NavItem();
            n.setNavId(items[i][0]);
            n.setLabel(items[i][1]);
            n.setHref(items[i][2]);
            n.setSortOrder(i + 1);
            em.persist(n);
        }
    }

    private void seedTeamInfo() {
        TeamInfo info = new TeamInfo();
        info.setEyebrow("团队概览");
        info.setTitle("我们是谁");
        info.setSubtitle("一群想把论文写在祖国大地上的年轻人");
        info.setDescription("鹿映秦华实践队由来自多所高校的青年学子组成，聚焦甘肃秦安乡村振兴一线，以影像记录、田野调研、产品助销为核心，用青年视角讲述乡土故事。");
        info.setMissionTitle("以镜头为笔，<br />以土地为卷，<br />书写乡村的当代叙事。");
        info.setMissionDesc("我们相信，乡村振兴不只是基建与产业，更是文化与记忆的延续。鹿映秦华以青年视角重新发现乡土价值，让每寸土地的故事被看见。");
        info.setMotto("\" 用镜头记录变迁，用脚步丈量土地，用青春回应时代。\"");
        em.persist(info);
    }

    private void seedTeamStats() {
        Object[][] stats = {
            {"核心队员", "12", "", false},
            {"实践天数", "30", "", false},
            {"调研村落", "5", "", false},
            {"纪实影像", "2K+", "", true},
        };
        for (int i = 0; i < stats.length; i++) {
            TeamStat s = new TeamStat();
            s.setLabel((String) stats[i][0]);
            s.setValue((String) stats[i][1]);
            s.setSuffix((String) stats[i][2]);
            s.setIsText((Boolean) stats[i][3]);
            s.setSortOrder(i + 1);
            em.persist(s);
        }
    }

    private void seedTeamAchievements() {
        Object[][] achievements = {
            {"12", "篇调研报告"},
            {"8", "场助农直播"},
            {"50w+", "传播曝光"},
            {"3", "项合作意向"},
        };
        for (int i = 0; i < achievements.length; i++) {
            TeamAchievement a = new TeamAchievement();
            a.setValue((String) achievements[i][0]);
            a.setLabel((String) achievements[i][1]);
            a.setSortOrder(i + 1);
            em.persist(a);
        }
    }

    private void seedCultureItems() {
        // Item 1: 陇右文脉
        CultureItem c1 = new CultureItem();
        c1.setSubtitleTag("文脉");
        c1.setTitle("陇右文脉");
        c1.setDescription("天水古称秦州，是华夏文明的重要发源地之一。伏羲画卦、女娲补天、大地湾遗址皆根植于此，千年文脉绵延不绝，陇右文化独树一帜。");
        c1.setImagePlaceholder("麦积山石窟 · 世界文化遗产");
        c1.setSortOrder(1);
        em.persist(c1);
        addCultureTag(c1, "大地湾遗址", "八千年文明曙光", 1);
        addCultureTag(c1, "伏羲文化", "人文始祖故里", 2);
        addCultureTag(c1, "麦积山石窟", "东方雕塑馆", 3);
        addCultureTag(c1, "陇右古道", "丝路咽喉要冲", 4);

        // Item 2: 非遗民俗
        CultureItem c2 = new CultureItem();
        c2.setSubtitleTag("非遗");
        c2.setTitle("非遗民俗");
        c2.setDescription("秦安小曲悠扬婉转，秦安蜡花舞古朴动人，草编工艺代代相传。这些鲜活的非物质文化遗产，是秦安人日常生活中的艺术，也是地域文化的活态基因。");
        c2.setImagePlaceholder("秦安非遗 · 民俗文化纪实");
        c2.setSortOrder(2);
        em.persist(c2);
        addCultureTag(c2, "秦安小曲", "国家级非遗曲艺", 1);
        addCultureTag(c2, "蜡花舞", "千年祭祀舞蹈", 2);
        addCultureTag(c2, "草编工艺", "指尖上的传统", 3);
        addCultureTag(c2, "社火民俗", "黄土高原年味", 4);

        // Item 3: 乡土风貌
        CultureItem c3 = new CultureItem();
        c3.setSubtitleTag("地貌");
        c3.setTitle("乡土风貌");
        c3.setDescription("秦安地处陇中黄土高原腹地，葫芦河穿城而过。层叠梯田、厚重黄土、古朴村落共同构成了这片土地独特的自然肌理与乡土地貌。");
        c3.setImagePlaceholder("秦安梯田 · 黄土高原乡土风貌");
        c3.setSortOrder(3);
        em.persist(c3);
        addCultureTag(c3, "黄土梯田", "大地的等高线", 1);
        addCultureTag(c3, "葫芦河", "秦安母亲河", 2);
        addCultureTag(c3, "古村落", "明清建筑遗存", 3);
        addCultureTag(c3, "陇中地貌", "千沟万壑奇观", 4);
    }

    private void addCultureTag(CultureItem item, String name, String note, int order) {
        CultureTag t = new CultureTag();
        t.setName(name);
        t.setNote(note);
        t.setSortOrder(order);
        t.setCultureItem(item);
        em.persist(t);
    }

    private void seedProducts() {
        // Product 1: 水蜜桃
        Product p1 = new Product();
        p1.setSubLabel("核心产品 01");
        p1.setName("水蜜桃");
        p1.setNamePrefix("秦安");
        p1.setDescription("秦安水蜜桃因黄土高原独特的气候条件，光照充足、昼夜温差大，孕育出果肉细嫩、汁多味甜的极品蜜桃。每一颗都是秦安大地的甜蜜馈赠。");
        p1.setImagePlaceholder("秦安水蜜桃 · 实地拍摄");
        p1.setSortOrder(1);
        em.persist(p1);
        addProductTag(p1, "🍑 果肉细嫩", 1);
        addProductTag(p1, "💧 汁多味甜", 2);
        addProductTag(p1, "🎁 送礼佳品", 3);
        addProductTag(p1, "🏠 家庭鲜享", 4);
        addProductStat(p1, "年均日照", "1800", "h+", false, 1);
        addProductStat(p1, "昼夜温差", "15", "°C", false, 2);
        addProductStat(p1, "种植面积", "30", "万亩", false, 3);
        addProductStat(p1, "地理标志", "国家地标", "", true, 4);

        // Product 2: 花椒
        Product p2 = new Product();
        p2.setSubLabel("核心产品 02");
        p2.setName("花椒");
        p2.setNamePrefix("秦安");
        p2.setDescription("秦安花椒色泽鲜红、颗粒饱满、麻香浓郁，是国家地理标志保护产品。每一粒花椒都凝聚着秦安农人一整年的心血与坚守。");
        p2.setImagePlaceholder("秦安花椒 · 实地拍摄");
        p2.setSortOrder(2);
        em.persist(p2);
        addProductTag(p2, "🌶 麻香浓郁", 1);
        addProductTag(p2, "✨ 颗粒饱满", 2);
        addProductTag(p2, "🍲 川味必备", 3);
        addProductTag(p2, "🏭 食品加工", 4);
        addProductStat(p2, "特有品种", "大红袍", "", true, 1);
        addProductStat(p2, "种植面积", "16", "万亩", false, 2);
        addProductStat(p2, "品质认证", "国家地标", "", true, 3);
        addProductStat(p2, "从业农户", "2", "万+", false, 4);
    }

    private void addProductTag(Product product, String name, int order) {
        ProductTag t = new ProductTag();
        t.setName(name);
        t.setSortOrder(order);
        t.setProduct(product);
        em.persist(t);
    }

    private void addProductStat(Product product, String label, String value, String suffix, boolean isText, int order) {
        ProductStat s = new ProductStat();
        s.setLabel(label);
        s.setValue(value);
        s.setSuffix(suffix);
        s.setIsText(isText);
        s.setSortOrder(order);
        s.setProduct(product);
        em.persist(s);
    }

    private void seedDocumentary() {
        Object[][] items = {
            {"队员下乡走访农户", 1, 1, "7月12日 · 刘坪镇", "深入桃园与果农交流",
             "linear-gradient(135deg, #e8d5c4 0%, #d4a88c 40%, #c49a76 100%)", "🌳"},
            {"田间地头实地调研", 2, 1, "7月13日 · 兴国镇", "花椒种植基地采样",
             "linear-gradient(160deg, #c8d6cf 0%, #8aaa9a 50%, #6b9980 100%)", "🌾"},
            {"与村民深入交流", 1, 1, "7月14日 · 陇城镇", "入户访谈记录乡音",
             "linear-gradient(145deg, #d4c8b8 0%, #baa890 35%, #9e8b73 100%)", "🏘️"},
            {"合作社参观学习", 1, 2, "7月15日 · 郭嘉镇", "了解集体经济发展",
             "linear-gradient(150deg, #b8c8d0 0%, #8da5b0 45%, #6d8a96 100%)", "📋"},
            {"志愿服务纪实", 1, 1, "7月16日 · 叶堡镇", "助农采摘与包装",
             "linear-gradient(140deg, #e0d4c0 0%, #c4ad8c 40%, #a88b6a 100%)", "🍑"},
            {"乡村振兴座谈会", 1, 1, "7月17日 · 县乡村振兴局", "青年建言献策",
             "linear-gradient(155deg, #c0d4c8 0%, #8ab89a 50%, #6a9a7c 100%)", "🤝"},
            {"村小支教瞬间", 1, 1, "7月18日 · 莲花镇", "为留守儿童带去课堂",
             "linear-gradient(130deg, #d8ccc0 0%, #b8a080 38%, #948060 100%)", "📚"},
            {"团队每日复盘", 1, 1, "7月19日 · 驻地", "整理当日调研成果",
             "linear-gradient(148deg, #c4d0cc 0%, #8ea898 48%, #6c8880 100%)", "✍️"},
            {"丰收时节助农", 2, 1, "7月20日 · 王铺镇", "直播助销秦安蜜桃",
             "linear-gradient(142deg, #e0ccc0 0%, #c4a080 42%, #a88060 100%)", "📦"},
        };
        for (int i = 0; i < items.length; i++) {
            DocumentaryItem d = new DocumentaryItem();
            d.setLabel((String) items[i][0]);
            d.setColsSpan((Integer) items[i][1]);
            d.setRowsSpan((Integer) items[i][2]);
            d.setTimeLabel((String) items[i][3]);
            d.setNote((String) items[i][4]);
            d.setBgGradient((String) items[i][5]);
            d.setIcon((String) items[i][6]);
            d.setSortOrder(i + 1);
            em.persist(d);
        }
    }

    private void seedStories() {
        String[][] stories = {
            {"走进田间地头", "队员们深入秦安多个村庄，走进果园、椒田，与农户面对面交流，了解产业发展现状与真实需求。"},
            {"记录乡土变迁", "用镜头记录秦安传统村落的新旧更迭，见证乡村振兴战略给这片土地带来的深刻变化。"},
            {"青春赋能乡土", "发挥青年学子的专业优势，为秦安农产品品牌化、电商化提供方案建议与技术支撑。"},
        };
        for (int i = 0; i < stories.length; i++) {
            Story s = new Story();
            s.setTitle(stories[i][0]);
            s.setDescription(stories[i][1]);
            s.setSortOrder(i + 1);
            em.persist(s);
        }
    }
}
