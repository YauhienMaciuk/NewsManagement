var counter = 0;
var offset = 0;
var limitNews = 20;
var lang = $
		.cookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE")
		|| navigator.language || navigator.userLanguage;
var conditionSearch = "page";
var idAuthor = 0;
var idTag = " ";
var idNews = 0;
var offsetComment = 0;
var limitComment = 20;
var limitNewsByComment = 0;
var previosOffset = 0;
var listTag = new Array();
var listAuthor = new Array();
var possibleSearchPage = false;
var possiblePage = false;
var possiblePageComments = false;
lang = lang.split("-")[0];

$(function() {
	initPage();
});

$(window).on("popstate", function(event) {
	var state = event.originalEvent.state.action;
	switch (state) {
	case "showFormNews":
		showFormNews();
		break;
	case "correctNews":
		var idUpdatingNews = event.originalEvent.state.action_id;
		correctNews(idUpdatingNews);
		break;
	case "viewSingleNews":
		var idSingleNews = event.originalEvent.state.action_id;
		viewSingleNews(idSingleNews);
		break;
	case "getAllComments":
		var idNewsForComment = event.originalEvent.state.action_id;
		getAllComments(idNewsForComment);
		break;
	case "viewAllNews":
		counter = 0;
		offset = event.originalEvent.state.offsetHistory;
		showAllNews();
		break;
	case "showNewsByTop100Comments":
		counter = 0;
		offset = event.originalEvent.state.offsetHistory;
		newsByComments();
		break;
	case "getAllNewsByTag":
		counter = 0;
		offset = event.originalEvent.state.offsetHistory;
		newsByTag();
		break;
	case "getAllNewsByAuthor":
		counter = 0;
		offset = event.originalEvent.state.offsetHistory;
		newsByAuthor();
		break;
	case "showNewsByTop20Comments":
		counter = 0;
		newsByTop20Comments();
		break;
	case "showNewsByTop5Comments":
		counter = 0;
		newsByTop5Comments();
		break;
	}
});

function initPage() {
	conditionSearch = "page";
	offset = 0;
	limitNews = 20;
	idNews = 0;
	showAllNews();
};

function newsByComments() {
	initStaticResource();
	$(".content").empty();
	conditionSearch = "byComment";
	getNewsByTop100Comments();
}

function newsByTag() {
	initStaticResource();
	$(".content").empty();
	conditionSearch = "tag";
	getAllNewsByTag();
}

function newsByAuthor() {
	initStaticResource();
	$(".content").empty();
	conditionSearch = "author";
	getAllNewsByAuthor();
}

function newsByTop20Comments() {
	initStaticResource();
	$(".content").empty();
	conditionSearch = "byComment";
	showNewsByTop20Comments();
}

function newsByTop5Comments() {
	initStaticResource();
	$(".content").empty();
	conditionSearch = "byComment";
	showNewsByTop5Comments();
}

function initStaticResource() {
	$("header").load("html/header.html", function() {
		$("#back").hide();
	});
	$(".menu").load("html/menu.html .add", function() {
		$(".showListNews").click(function() {
			initPage();
		});
		$(".addNews").click(function() {
			showFormNews();
		});
		$(".searchNewsByTop5Comments").click(function() {
			showNewsByTop5Comments();
		});
		$(".searchNewsByTop20Comments").click(function() {
			showNewsByTop20Comments();
		});
		$(".searchNewsByTop100Comments").click(function() {
			showNewsByTop100Comments();
		});
		getAllTags();
		getAllAuthors();
		changeLang(lang);
	});
}

function showAllNews() {
	initStaticResource();
	$(".content").load("html/body.html #news");
	$("footer").load("html/footer.html");
	viewAllNews();
	$.i18n.init({
		lng : lang
	}, function() {
		$("body").i18n();
	});
}

function changeLang(choosedLang) {
	lang = choosedLang;
	$.cookie(
			"org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE",
			lang);
	$.i18n.setLng(lang, function() {
		$("body").i18n();
	});
};

function actionBack() {
	counter = counter + 1;
	history.go(length - counter);
	return false;
};
