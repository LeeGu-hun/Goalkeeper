$(document).ready(function() {
		var availableTags = [
            '가나',
            '가나쵸콜렛',
            '갈갈이 삼형제',
            '북마크',
            '북까페',
            '엄마',
            '아빠',
            '북소리',
            '여러분',
            '소문',
            '소문난 식당',
            '나나나',
            '쇼',
            '쇼팽',
            '모나미',
            '한강',
            '강강수월래',
            '강촌',
            '제주도',
            '삼총사',
            '먹보',
            '먹소리',
            '수박',
            '수박 겉핥기'
        ];

		$("#searchbox").autocomplete(availableTags,{ 
		matchContains: true,
		selectFirst: false
		});
	});