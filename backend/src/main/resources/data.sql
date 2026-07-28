-- ============================================================
-- 鹿映秦华 种子数据 —— 与前端 App.vue 硬编码数据完全一致
-- ============================================================

-- ====== 导航 ======
INSERT INTO nav_item (nav_id, label, href, sort_order) VALUES
('team', '团队', '#team', 1),
('culture', '秦安文化', '#culture', 2),
('products', '农产品', '#products', 3),
('documentary', '实践纪实', '#documentary', 4),
('closing', '关于', '#closing', 5);

-- ====== 团队介绍 ======
INSERT INTO team_info (eyebrow, title, subtitle, description, mission_title, mission_desc, motto) VALUES
('团队概览', '我们是谁', '一群想把论文写在祖国大地上的年轻人',
 '鹿映秦华实践队由来自多所高校的青年学子组成，聚焦甘肃秦安乡村振兴一线，以影像记录、田野调研、产品助销为核心，用青年视角讲述乡土故事。',
 '以镜头为笔，<br />以土地为卷，<br />书写乡村的当代叙事。',
 '我们相信，乡村振兴不只是基建与产业，更是文化与记忆的延续。鹿映秦华以青年视角重新发现乡土价值，让每寸土地的故事被看见。',
 '" 用镜头记录变迁，用脚步丈量土地，用青春回应时代。"');

-- ====== 团队核心数据 ======
INSERT INTO team_stat (label, value, suffix, is_text, sort_order) VALUES
('核心队员', '12', '', false, 1),
('实践天数', '30', '', false, 2),
('调研村落', '5', '', false, 3),
('纪实影像', '2K+', '', true, 4);

-- ====== 团队成果 ======
INSERT INTO team_achievement (value, label, sort_order) VALUES
('12', '篇调研报告', 1),
('8', '场助农直播', 2),
('50w+', '传播曝光', 3),
('3', '项合作意向', 4);

-- ====== 文化板块 ======
INSERT INTO culture_item (subtitle_tag, title, description, image_placeholder, sort_order) VALUES
('文脉', '陇右文脉',
 '天水古称秦州，是华夏文明的重要发源地之一。伏羲画卦、女娲补天、大地湾遗址皆根植于此，千年文脉绵延不绝，陇右文化独树一帜。',
 '麦积山石窟 · 世界文化遗产', 1);

INSERT INTO culture_item (subtitle_tag, title, description, image_placeholder, sort_order) VALUES
('非遗', '非遗民俗',
 '秦安小曲悠扬婉转，秦安蜡花舞古朴动人，草编工艺代代相传。这些鲜活的非物质文化遗产，是秦安人日常生活中的艺术，也是地域文化的活态基因。',
 '秦安非遗 · 民俗文化纪实', 2);

INSERT INTO culture_item (subtitle_tag, title, description, image_placeholder, sort_order) VALUES
('地貌', '乡土风貌',
 '秦安地处陇中黄土高原腹地，葫芦河穿城而过。层叠梯田、厚重黄土、古朴村落共同构成了这片土地独特的自然肌理与乡土地貌。',
 '秦安梯田 · 黄土高原乡土风貌', 3);

-- ====== 文化标签（每项4个） ======
-- 陇右文脉 标签
INSERT INTO culture_tag (name, note, sort_order, culture_item_id) VALUES
('大地湾遗址', '八千年文明曙光', 1, 1),
('伏羲文化', '人文始祖故里', 2, 1),
('麦积山石窟', '东方雕塑馆', 3, 1),
('陇右古道', '丝路咽喉要冲', 4, 1);

-- 非遗民俗 标签
INSERT INTO culture_tag (name, note, sort_order, culture_item_id) VALUES
('秦安小曲', '国家级非遗曲艺', 1, 2),
('蜡花舞', '千年祭祀舞蹈', 2, 2),
('草编工艺', '指尖上的传统', 3, 2),
('社火民俗', '黄土高原年味', 4, 2);

-- 乡土风貌 标签
INSERT INTO culture_tag (name, note, sort_order, culture_item_id) VALUES
('黄土梯田', '大地的等高线', 1, 3),
('葫芦河', '秦安母亲河', 2, 3),
('古村落', '明清建筑遗存', 3, 3),
('陇中地貌', '千沟万壑奇观', 4, 3);

-- ====== 农产品 ======
INSERT INTO product (sub_label, name, name_prefix, description, image_placeholder, sort_order) VALUES
('核心产品 01', '水蜜桃', '秦安',
 '秦安水蜜桃因黄土高原独特的气候条件，光照充足、昼夜温差大，孕育出果肉细嫩、汁多味甜的极品蜜桃。每一颗都是秦安大地的甜蜜馈赠。',
 '秦安水蜜桃 · 实地拍摄', 1);

INSERT INTO product (sub_label, name, name_prefix, description, image_placeholder, sort_order) VALUES
('核心产品 02', '花椒', '秦安',
 '秦安花椒色泽鲜红、颗粒饱满、麻香浓郁，是国家地理标志保护产品。每一粒花椒都凝聚着秦安农人一整年的心血与坚守。',
 '秦安花椒 · 实地拍摄', 2);

-- ====== 产品标签 ======
-- 水蜜桃标签
INSERT INTO product_tag (name, sort_order, product_id) VALUES
('🍑 果肉细嫩', 1, 1),
('💧 汁多味甜', 2, 1),
('🎁 送礼佳品', 3, 1),
('🏠 家庭鲜享', 4, 1);

-- 花椒标签
INSERT INTO product_tag (name, sort_order, product_id) VALUES
('🌶 麻香浓郁', 1, 2),
('✨ 颗粒饱满', 2, 2),
('🍲 川味必备', 3, 2),
('🏭 食品加工', 4, 2);

-- ====== 产品数据 ======
-- 水蜜桃数据
INSERT INTO product_stat (label, value, suffix, is_text, sort_order, product_id) VALUES
('年均日照', '1800', 'h+', false, 1, 1),
('昼夜温差', '15', '°C', false, 2, 1),
('种植面积', '30', '万亩', false, 3, 1),
('地理标志', '国家地标', '', true, 4, 1);

-- 花椒数据
INSERT INTO product_stat (label, value, suffix, is_text, sort_order, product_id) VALUES
('特有品种', '大红袍', '', true, 1, 2),
('种植面积', '16', '万亩', false, 2, 2),
('品质认证', '国家地标', '', true, 3, 2),
('从业农户', '2', '万+', false, 4, 2);

-- ====== 实践纪实照片 ======
INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('队员下乡走访农户', 1, 1, '7月12日 · 刘坪镇', '深入桃园与果农交流',
 'linear-gradient(135deg, #e8d5c4 0%, #d4a88c 40%, #c49a76 100%)', '🌳', 1);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('田间地头实地调研', 2, 1, '7月13日 · 兴国镇', '花椒种植基地采样',
 'linear-gradient(160deg, #c8d6cf 0%, #8aaa9a 50%, #6b9980 100%)', '🌾', 2);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('与村民深入交流', 1, 1, '7月14日 · 陇城镇', '入户访谈记录乡音',
 'linear-gradient(145deg, #d4c8b8 0%, #baa890 35%, #9e8b73 100%)', '🏘️', 3);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('合作社参观学习', 1, 2, '7月15日 · 郭嘉镇', '了解集体经济发展',
 'linear-gradient(150deg, #b8c8d0 0%, #8da5b0 45%, #6d8a96 100%)', '📋', 4);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('志愿服务纪实', 1, 1, '7月16日 · 叶堡镇', '助农采摘与包装',
 'linear-gradient(140deg, #e0d4c0 0%, #c4ad8c 40%, #a88b6a 100%)', '🍑', 5);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('乡村振兴座谈会', 1, 1, '7月17日 · 县乡村振兴局', '青年建言献策',
 'linear-gradient(155deg, #c0d4c8 0%, #8ab89a 50%, #6a9a7c 100%)', '🤝', 6);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('村小支教瞬间', 1, 1, '7月18日 · 莲花镇', '为留守儿童带去课堂',
 'linear-gradient(130deg, #d8ccc0 0%, #b8a080 38%, #948060 100%)', '📚', 7);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('团队每日复盘', 1, 1, '7月19日 · 驻地', '整理当日调研成果',
 'linear-gradient(148deg, #c4d0cc 0%, #8ea898 48%, #6c8880 100%)', '✍️', 8);

INSERT INTO documentary_item (label, cols_span, rows_span, time_label, note, bg_gradient, icon, sort_order) VALUES
('丰收时节助农', 2, 1, '7月20日 · 王铺镇', '直播助销秦安蜜桃',
 'linear-gradient(142deg, #e0ccc0 0%, #c4a080 42%, #a88060 100%)', '📦', 9);

-- ====== 纪实故事 ======
INSERT INTO story (title, description, sort_order) VALUES
('走进田间地头', '队员们深入秦安多个村庄，走进果园、椒田，与农户面对面交流，了解产业发展现状与真实需求。', 1),
('记录乡土变迁', '用镜头记录秦安传统村落的新旧更迭，见证乡村振兴战略给这片土地带来的深刻变化。', 2),
('青春赋能乡土', '发挥青年学子的专业优势，为秦安农产品品牌化、电商化提供方案建议与技术支撑。', 3);
