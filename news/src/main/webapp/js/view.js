function viewAllNews() {
	var offsetHistory = 0;
	history.pushState({
		action : "viewAllNews",
		offsetHistory : offset
	}, "", "");
	$(".content").empty();
	limitNews = 20;
	conditionSearch = "page";
	getAllNews();
};

function showNewsByTop5Comments() {
	history.pushState({
		action : "showNewsByTop5Comments"
	}, "", "");
	limitNewsByComment = 5;
	offset = 0;
	$(".content").empty();
	conditionSearch = "byComment";
	getNewsByTop5_20Comments();
}

function showNewsByTop20Comments() {
	history.pushState({
		action : "showNewsByTop20Comments"
	}, "", "");
	limitNewsByComment = 20;
	offset = 0;
	$(".content").empty();
	conditionSearch = "byComment";
	getNewsByTop5_20Comments();
}

function showNewsByTop100Comments() {
	limitNews = 20;
	offset = 0;
	historyNewsByTop100Comments();
	$(".content").empty();
	conditionSearch = "byComment";
	getNewsByTop100Comments();
}

function correctNews(id) {
	history.pushState({
		action_id : id,
		action : "correctNews"
	}, "", "");
	$("#back").show();
	$(".content").load("html/body.html #updateForm", function() {
		doCorrectNews(id);
	});
};

function showUpdateFormNews(news) {
	$.each(news["listTags"], function(index, tag) {
		$("#tags").append(tag["name"] + " ");
	});
	$("#add-title").append(news["title"]);
	$("#add-description").append(news["description"]);
	$("#add-fullText").append(news["fullText"]);
	$.each(news["listAuthors"], function(index, author) {
		$("#authors").append(author["name"] + " ");
	});
	$("#idNews").val(news["id"]);
	$("#creationTime").val(news["creationTime"]);
	listTag.length = 0;
	$.each(news.listTags, function(index, value) {
		var tag = value.name;
		listTag.push(tag);
	})
	listAuthor.length = 0;
	$.each(news.listAuthors, function(index, value) {
		var author = value.name;
		listAuthor.push(author);
	})
	idNews = news["id"];
	getAllComments();
	$(".menu").load("html/menu.html .menu-editNews", function() {
		$(".showListNews").click(function() {
			initPage();
		});
		$(".deleteNews").click(function() {
			deleteNews(id);
		});
		getAllTagsForUpdateNews();
		getAllAuthorsForUpdateNews();
		changeLang(lang);
	});
}

function updateNews() {
	var tags = [];
	$('.tags input:checkbox:checked').each(function() {
		var tag = {
			name : $(this).val()
		}
		tags.push(tag);
	});
	var authors = [];
	$('.authors input:checkbox:checked').each(function() {
		var author = {
			id : $(this).val()
		}
		authors.push(author);
	});
	var news = {
		id : $("#idNews").val(),
		title : $("#add-title").val(),
		description : $("#add-description").val(),
		fullText : $("#add-fullText").val(),
		listAuthors : authors,
		listTags : tags
	}
	var message = $.i18n.t("confirm.updateNews");
	if (confirm(message)) {
		doUpdateNews(news);
	}
};

function viewSingleNews(id) {
	history.pushState({
		action_id : id,
		action : "viewSingleNews"
	}, "", "");
	$("#back").show();
	$(".content").load("html/body.html #singleNews", function() {
		doViewSingleNews(id);
	});
	$(".menu").load("html/menu.html .menu-singleNews", function() {
		$(".showListNews").click(function() {
			initPage();
		});
		$(".addNews").click(function() {
			showFormNews();
		});
		$(".deleteNews").click(function() {
			deleteNews(id);
		});
		$(".editNews").click(function() {
			correctNews(id);
		});
		changeLang(lang);
	});
};

function showSingleNews(news) {
	$.each(news["listTags"], function(index, tag) {
		$("#tags").append(tag["name"] + " ");
	});
	$(".title").text(news["title"]);
	$(".description").text(news["description"]);
	$(".fullText").text(news["fullText"]);
	idNews = news["id"];
	$("#idNewsForCreateComment").val(news["id"]);
	$.each(news["listAuthors"], function(index, author) {
		$("#authors").append(author["name"] + " ");
	});
	changeLang(lang);
	getAllComments();
}

function deleteNews(id) {
	var message = $.i18n.t("confirm.deleteNews");
	if (confirm(message)) {
		doDeleteNews(id);
	}
};

function searchPageForAllNews() {
	var requiredPage = $(".numberPage").val();
	previosOffset = offset;
	offset = requiredPage * 20 - 20;
	conditionSearch = "page";
	checkPossibleSearchPageNews();
}

function searchPageForNewsByTag() {
	var requiredPage = $(".numberPage").val();
	previosOffset = offset;
	offset = requiredPage * 20 - 20;
	conditionSearch = "tag";
	checkPossibleSearchPageNewsByTag();
}

function searchPageForNewsByAuthor() {
	var requiredPage = $(".numberPage").val();
	previosOffset = offset;
	offset = requiredPage * 20 - 20;
	conditionSearch = "author";
	checkPossibleSearchPageNewsByAuthor();
}

function searchPageForNewsByComments() {
	var requiredPage = $(".numberPage").val();
	previosOffset = offset;
	offset = requiredPage * 20 - 20;
	conditionSearch = "byComment";
	checkPossibleSearchPageNewsByComments();
}

function afterCheckPossibleSearchPageNews() {
	if (possibleSearchPage == true) {
		viewAllNews();
	} else {
		offset = previosOffset;
		var message = $.i18n.t("info.notFound");
		alert(message);
	}
}

function afterCheckPossibleSearchPageNewsByTag() {
	if (possibleSearchPage == true) {
		historyNewsByTags();
		$(".content").empty();
		getAllNewsByTag();
	} else {
		offset = previosOffset;
		var message = $.i18n.t("info.notFound");
		alert(message);
	}
}

function afterCheckPossibleSearchPageNewsByAuthor() {
	if (possibleSearchPage == true) {
		historyNewsByAuthor();
		$(".content").empty();
		getAllNewsByAuthor();
	} else {
		offset = previosOffset;
		var message = $.i18n.t("info.notFound");
		alert(message);
	}
}

function afterCheckPossibleSearchPageNewsByComments() {
	if (possibleSearchPage == true) {
		historyNewsByTop100Comments();
		$(".content").empty();
		getNewsByTop100Comments();
	} else {
		offset = previosOffset;
		var message = $.i18n.t("info.notFound");
		alert(message);
	}
}

function addNews() {
	var tags = [];
	$('.tags input:checkbox:checked').each(function() {
		var tag = {
			name : $(this).val()
		}
		tags.push(tag);
	});
	var authors = [];
	$('.authors input:checkbox:checked').each(function() {
		var author = {
			id : $(this).val()
		}
		authors.push(author);
	});
	var news = {
		title : $("#title").val(),
		description : $("#description").val(),
		fullText : $("#fullText").val(),
		listAuthors : authors,
		listTags : tags
	}
	doAddNews(news);
};

function viewAllTagsForCreateNews(index, tag) {
	$(".tags").append(
			"<p><input type='checkbox' value='" + tag['name'] + "'>"
					+ tag['name'] + "</p>");
}

function viewAllAuthorsForCreateNews(index, author) {
	$(".authors").append(
			"<p><input type='checkbox' value='" + author["id"] + "'>"
					+ author['name'] + "</p>");
}

function viewAllTagsForUpdateNews(index, tag) {
	var check = 0;
	$.each(listTag, function(index, value) {
		if (tag.name == value) {
			check = 1;
		}
	})
	if (check == 1) {
		$(".tags").append(
				"<p><input type='checkbox' value='" + tag['name']
						+ "' checked>" + tag['name'] + "</p>");
	} else {
		$(".tags").append(
				"<p><input type='checkbox' value='" + tag['name'] + "'>"
						+ tag['name'] + "</p>");
	}
}

function viewAllAuthorsForUpdateNews(index, author) {
	var check = 0;
	$.each(listAuthor, function(index, value) {
		if (author.name == value) {
			check = 1;
		}
	})
	if (check == 1) {
		$(".authors").append(
				"<p><input type='checkbox' value='" + author["id"]
						+ "' checked>" + author['name'] + "</p>");
	} else {
		$(".authors").append(
				"<p><input type='checkbox' value='" + author["id"] + "'>"
						+ author['name'] + "</p>");
	}
}

function showFormNews() {
	history.pushState({
		action : "showFormNews"
	}, "", "");
	$("#back").show();
	$(".menu").load("html/menu.html .menu-formAddNews", function() {
		$(".showListNews").click(function() {
			initPage();
		});
		getAllTagsForCreateNews();
		getAllAuthorsForCreateNews();
		changeLang(lang);
	});
	$(".content").load("html/body.html #formAddNews", function() {
		changeLang(lang);
	});
};

function showListNews(index, news) {
	$(".content").append("<div class='newsElement' hight='200'></div>");
	$(".newsElement").last().load("html/listNews.html", function() {
		$(".modificationTime").last().text(news.modificationTime);
		$(".title").last().text(news.title);
		$(".description").last().text(news.description);
		$(".listNews-delete").last().click(function() {
			deleteNews(news.id);
		});
		$(".listNews-view").last().click(function() {
			viewSingleNews(news.id);
		});
		$(".listNews-edit").last().click(function() {
			correctNews(news.id);
		});
		changeLang(lang);
	});
}

function addComment() {
	var comment = {
		text : $("#textareaComment").val()
	}
	doAddComment(comment);
}

function addTag() {
	var newTag = {
		name : $("#createTag").val()
	}
	doAddTag(newTag);
}

function addAuthor() {
	var author = {
		name : $("#createAuthor").val()
	}
	doAddAuthor(author);
}

function novigationComments(listNews) {
	$(".content").append("<div class='novigationNews'></div>");
	if (offsetComment == 0) {
		offsetComment = 20;
		checkPossibleNextForFirstPageComments();
	} else {
		offsetComment = offsetComment + 20;
		checkPossibleNextForAllPageComments();
	}
}

function novigationNews(listNews) {
	$(".content").append("<div class='novigationNews'></div>");
	if (offset == 0) {
		offset = 20;
		checkPossibleNextPageForFirstPage();
	} else {
		offset = offset + 20;
		checkPossibleNextPageForAllPage();
	}
}

function novigationNewsByAuthor(listNews) {
	$(".novigationNews").empty();
	$(".content").append("<div class='novigationNews'></div>");
	if (offset == 0) {
		offset = 20;
		checkPossibleNextPageForFirstPageByAuthor();
	} else {
		offset = offset + 20;
		checkPossibleNextPageForAllPageByAuthor();
	}
}

function novigationNewsByTag(listNews) {
	$(".novigationNews").empty();
	$(".content").append("<div class='novigationNews'></div>");
	if (offset == 0) {
		offset = 20;
		checkPossibleNextPageForFirstPageByTag();
	} else {
		offset = offset + 20;
		checkPossibleNextPageForAllPageByTag();
	}
}

function novigationWithoutButtonNextPageForFirstPage() {
	offset = 0;
	$(".novigationNews").load(
			"html/novigation.html #allNewsFirstPageWithoutNextPage",
			function() {
				changeLang(lang);
			})
}

function novigationWithButtonNextPageForFirstPage() {
	offset = 0;
	$(".novigationNews").load("html/novigation.html #allNewsFirstPage",
			function() {
				$(".nextPage").html($.i18n.t("content.nextPage"));
				$(".novigation").text("1");
				changeLang(lang);
			})
}

function novigationWithoutButtonNextForFirstPageComments() {
	offsetComment = offsetComment - 20;
	$(".novigationNews").load(
			"html/novigation.html #allCommentsFirstPageWithoutNextPage")
}

function novigationWithButtonNextForFirstPageComments() {
	offsetComment = offsetComment - 20;
	$(".novigationNews").load("html/novigation.html #allCommentsFirstPage",
			function() {
				var numberPage = offsetComment / 20;
				$(".novigation").last().text(++numberPage);
				$(".nextPage").html($.i18n.t("content.nextPage"));
				changeLang(lang);
			})
}

function novigationWithoutButtonNextForComments() {
	offsetComment = offsetComment - 20;
	$(".novigationNews").load(
			"html/novigation.html #allCommentsWithoutNextPage", function() {
				var numberPage = offsetComment / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				changeLang(lang);
			})
}

function novigationWithButtonNextForComments() {
	offsetComment = offsetComment - 20;
	$(".novigationNews").load("html/novigation.html #allComments", function() {
		var numberPage = offsetComment / 20;
		$(".prevPage").html($.i18n.t("content.prevPage"));
		$(".novigation").last().text(++numberPage);
		$(".nextPage").html($.i18n.t("content.nextPage"));
		changeLang(lang);
	})
}

function novigationWithoutButtonNextPage() {
	offset = offset - 20;
	$(".novigationNews").load("html/novigation.html #allNewsWithoutNextPage",
			function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				changeLang(lang);
			})
}

function novigationWithButtonNextPage() {
	offset = offset - 20;
	$(".novigationNews").load("html/novigation.html #allNews", function() {
		var numberPage = offset / 20;
		$(".prevPage").html($.i18n.t("content.prevPage"));
		$(".novigation").last().text(++numberPage);
		$(".nextPage").html($.i18n.t("content.nextPage"));
		changeLang(lang);
	})
}

function novigationWithoutButtonNextPageForFirstPageByTag() {
	offset = 0;
	$(".novigationNews").load(
			"html/novigation.html #allNewsFirstPageWithoutNextPage")
}

function novigationWithButtonNextPageForFirstPageByTag() {
	offset = 0;
	$(".novigationNews").load("html/novigation.html #allNewsByTagFirstPage",
			function() {
				$(".nextPage").html($.i18n.t("content.nextPage"));
				$(".novigation").text("1");
				changeLang(lang);
			})
}

function novigationWithoutButtonNextPageByTag() {
	offset = offset - 20;
	$(".novigationNews").load(
			"html/novigation.html #allNewsByTagWithoutNextPage", function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				changeLang(lang);
			})
}

function novigationWithButtonNextPageByTag() {
	offset = offset - 20;
	$(".novigationNews").load("html/novigation.html #allNewsByTag", function() {
		var numberPage = offset / 20;
		$(".prevPage").html($.i18n.t("content.prevPage"));
		$(".novigation").last().text(++numberPage);
		$(".nextPage").html($.i18n.t("content.nextPage"));
		changeLang(lang);
	})
}

function novigationWithoutButtonNextPageForFirstPageByAuthor() {
	offset = 0;
	$(".novigationNews").load(
			"html/novigation.html #allNewsFirstPageWithoutNextPage")
}

function novigationWithButtonNextPageForFirstPageByAuthor() {
	offset = 0;
	$(".novigationNews").load("html/novigation.html #allNewsByAuthorFirstPage",
			function() {
				$(".nextPage").html($.i18n.t("content.nextPage"));
				$(".novigation").text("1");
				changeLang(lang);
			})
}

function novigationWithoutButtonNextPageByAuthor() {
	offset = offset - 20;
	$(".novigationNews").load(
			"html/novigation.html #allNewsByAuthorWithoutNextPage", function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				changeLang(lang);
			})
}

function novigationWithButtonNextPageByAuthor() {
	offset = offset - 20;
	$(".novigationNews").load("html/novigation.html #allNewsByAuthor",
			function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				$(".nextPage").html($.i18n.t("content.nextPage"));
				changeLang(lang);
			})
}

function novigationNewsByTop100Comments(listNews) {
	$(".content").append("<div class='novigationNews'></div>");
	if (offset == 0) {
		offset = 20;
		checkPossibleNextPageForFirstPageByTop100Comments();
	} else if (offset == 80) {
		novigationLastPageByTop100Comments();
	} else {
		offset = offset + 20;
		checkPossibleNextPageForAllPageByTop100Comments();
	}
}

function novigationWithoutButtonNextPageForFirstPageByTop100Comments() {
	offset = 0;
	$(".novigationNews").load(
			"html/novigation.html #allNewsFirstPageWithoutNextPage")
}

function novigationWithButtonNextPageForFirstPageByTop100Comments() {
	offset = 0;
	$(".novigationNews").load(
			"html/novigation.html #allNewsByTop100CommentsFirstPage",
			function() {
				$(".nextPage").html($.i18n.t("content.nextPage"));
				$(".novigation").text("1");
				changeLang(lang);
			})
}

function novigationWithoutButtonNextPageByTop100Comments() {
	offset = offset - 20;
	$(".novigationNews").load(
			"html/novigation.html #allNewsByTop100CommentsWithoutNextPage",
			function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				changeLang(lang);
			})
}

function novigationWithButtonNextPageByTop100Comments() {
	offset = offset - 20;
	$(".novigationNews").load("html/novigation.html #allNewsByTop100Comments",
			function() {
				var numberPage = offset / 20;
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text(++numberPage);
				$(".nextPage").html($.i18n.t("content.nextPage"));
				changeLang(lang);
			})
}

function novigationLastPageByTop100Comments() {
	$(".novigationNews").load(
			"html/novigation.html #allNewsByTop100CommentsWithoutNextPage",
			function() {
				$(".prevPage").html($.i18n.t("content.prevPage"));
				$(".novigation").last().text("5");
				changeLang(lang);
			})
}

function previousPage() {
	$(".content").empty();
	offset = offset - 20;
	viewAllNews();
}

function nextPage() {
	$(".content").empty();
	offset = offset + 20;
	viewAllNews();
}

function nextPageByTag() {
	$(".content").empty();
	offset = offset + 20;
	historyNewsByTags();
	getAllNewsByTag();
}

function previousPageByTag() {
	$(".content").empty();
	offset = offset - 20;
	historyNewsByTags();
	getAllNewsByTag();
}

function nextPageByAuthor() {
	$(".content").empty();
	offset = offset + 20;
	historyNewsByAuthor();
	getAllNewsByAuthor();
}

function previousPageByAuthor() {
	$(".content").empty();
	offset = offset - 20;
	historyNewsByAuthor();
	getAllNewsByAuthor();
}

function previousPageByTop100Comments() {
	$(".content").empty();
	offset = offset - 20;
	historyNewsByTop100Comments();
	getNewsByTop100Comments();
}

function nextPageByTop100Comments() {
	$(".content").empty();
	offset = offset + 20;
	historyNewsByTop100Comments();
	getNewsByTop100Comments();
}

function historyNewsByTags() {
	var offsetHistory = 0;
	history.pushState({
		action : "getAllNewsByTag",
		offsetHistory : offset
	}, "", "");
}

function historyNewsByAuthor() {
	var offsetHistory = 0;
	history.pushState({
		action : "getAllNewsByAuthor",
		offsetHistory : offset
	}, "", "");
}

function historyNewsByTop100Comments() {
	var offsetHistory = 0;
	history.pushState({
		action : "showNewsByTop100Comments",
		offsetHistory : offset
	}, "", "");
}

function previousPageComments() {
	offsetComment = offsetComment - 20;
	getAllComments();
}

function nextPageComments() {
	offsetComment = offsetComment + 20;
	getAllComments();
}

function viewAllTags(index, tag) {
	$(".tags").append("<p id='tag" + index + "'>" + tag["name"] + " (" + tag["quantityNews"] + ")</p>");
	$("#tag" + index).click(function() {
		getNewsByTag(tag.name);
	})
}

function viewAllAuthors(index, author) {
	$(".authors").append(
			"<p id='author" + index + "'>" + author["name"] + " (" + author["quantityNews"] + ")</p>");
	$("#author" + index).click(function() {
		getNewsByAuthor(author.id);
	})
}

function getNewsByTag(nameTag) {
	offset = 0;
	historyNewsByTags();
	idTag = nameTag;
	conditionSearch = "tag";
	$(".content").empty();
	getAllNewsByTag();
}

function getNewsByAuthor(idAuthorForSearch) {
	offset = 0;
	historyNewsByAuthor();
	idAuthor = idAuthorForSearch;
	conditionSearch = "author";
	$(".content").empty();
	getAllNewsByAuthor();
}

function showAllComments(index, comment) {
	$(".listComments").empty();
	$(".content").append("<div class='listComments'></div>");
	$(".listComments").last().load("html/formComment.html", function() {
		$(".modificationTime").last().text(comment.creationDate);
		$(".comment").last().text(comment.text);
		$(".forDeleteComment").last().click(function() {
			var message = $.i18n.t("confirm.deleteComment");
			if (confirm(message)) {
				deleteComment(comment.id);
			}
		});
		changeLang(lang);
	});
}

function showPageAfterAddTag(tag) {
	var message = $.i18n.t("confirm.createdTag");
	confirm(message + " - " + tag.name);
	$(".tags").empty();
	$("#createTag").val(" ");
	getAllTagsForCreateNews();
}

function showPageAfterAddAuthor(author) {
	var message = $.i18n.t("confirm.createdAuthor");
	confirm(message + " - " + author.name);
	$(".authors").empty();
	$("#createAuthor").val(" ");
	getAllAuthorsForCreateNews();
}

function showError(errorMessage) {
	var errors = errorMessage.responseJSON;
	var message = "";
	for (var i = 0; i < errors.length; i++) {
		message += errors[i];
		message += "\n";
	}
	confirm(message);
};