var URL_NEWS = "rest/news";
var URL_TAG = "rest/tag";
var URL_AUTHOR = "rest/author";
var URL_COMMENT = "rest/comment";

function getAllNews() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			$.each(listNews, showListNews);
			novigationNews(listNews);
			changeLang(lang);
		},
		error : function() {
			$("#news").html($.i18n.t("error.listNews"));
		}
	});
}

function checkPossibleSearchPageNews() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			if (listNews[0] != null) {
				possibleSearchPage = true;
			} else {
				possibleSearchPage = false;
			}
			afterCheckPossibleSearchPageNews();
		}
	});
}

function checkPossibleSearchPageNewsByTag() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			if (listNews[0] != null) {
				possibleSearchPage = true;
			} else {
				possibleSearchPage = false;
			}
			afterCheckPossibleSearchPageNewsByTag();
		}
	});
}

function checkPossibleSearchPageNewsByAuthor() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			if (listNews[0] != null) {
				possibleSearchPage = true;
			} else {
				possibleSearchPage = false;
			}
			afterCheckPossibleSearchPageNewsByAuthor();
		}
	});
}

function checkPossibleSearchPageNewsByComments() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			if (listNews[0] != null) {
				possibleSearchPage = true;
			} else {
				possibleSearchPage = false;
			}
			afterCheckPossibleSearchPageNewsByComments();
		}
	});
}

function getAllNewsByAuthor() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			$.each(listNews, showListNews);
			novigationNewsByAuthor(listNews);
			changeLang(lang);
		},
		error : function() {
			$("#news").html($.i18n.t("error.listNews"));
		}
	});
}

function getAllNewsByTag() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			$.each(listNews, showListNews);
			novigationNewsByTag(listNews);
			changeLang(lang);
		},
		error : function() {
			$("#news").html($.i18n.t("error.listNews"));
		}
	});
}

function getNewsByTop5_20Comments() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNewsByComment
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			$.each(listNews, showListNews);
			changeLang(lang);
		},
		error : function() {
			$("#news").html($.i18n.t("error.listNews"));
		}
	});
}

function getNewsByTop100Comments() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			$.each(listNews, showListNews);
			novigationNewsByTop100Comments(listNews);
			changeLang(lang);
		},
		error : function() {
			$("#news").html($.i18n.t("error.listNews"));
		}
	});
}

function checkPossibleNextForFirstPageComments() {
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/comment" + "/?language=" + lang
				+ "&offset=" + offsetComment + "&limit=" + limitComment,
		dataType : "json",
		success : function(listComments) {
			afterCheckPossibleNextForFirstPageComments(listComments);
		}
	});
}

function afterCheckPossibleNextForFirstPageComments(listComments) {
	var comment = listComments[0];
	if (comment == null) {
		novigationWithoutButtonNextForFirstPageComments();
	} else {
		novigationWithButtonNextForFirstPageComments();
	}
}

function checkPossibleNextForAllPageComments() {
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/comment" + "/?language=" + lang
				+ "&offset=" + offsetComment + "&limit=" + limitComment,
		dataType : "json",
		success : function(listComments) {
			afterCheckPossibleNextForAllPageComment(listComments);
		}
	});
}

function afterCheckPossibleNextForAllPageComment(listComments) {
	var comment = listComments[0];
	if (comment == null) {
		novigationWithoutButtonNextForComments();
	} else {
		novigationWithButtonNextForComments();
	}
}

function checkPossibleNextPageForFirstPage() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageForFirstPage(listNews);
		}
	});
}

function checkPossibleNextPageForAllPage() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPage(listNews);
		}
	});
}

function afterCheckPossibleNextPage(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPage();
	} else {
		novigationWithButtonNextPage();
	}
}

function afterCheckPossibleNextPageForFirstPage(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageForFirstPage();
	} else {
		novigationWithButtonNextPageForFirstPage();
	}
}

function checkPossibleNextPageForFirstPageByTag() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageForFirstPageByTag(listNews);
		}
	});
}

function checkPossibleNextPageForAllPageByTag() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageByTag(listNews);
		}
	});
}

function afterCheckPossibleNextPageByTag(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageByTag();
	} else {
		novigationWithButtonNextPageByTag();
	}
}

function afterCheckPossibleNextPageForFirstPageByTag(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageForFirstPageByTag();
	} else {
		novigationWithButtonNextPageForFirstPageByTag();
	}
}

function checkPossibleNextPageForFirstPageByTop100Comments() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageForFirstPageByTop100Comments(listNews);
		}
	});
}

function checkPossibleNextPageForAllPageByTop100Comments() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageByTop100Comments(listNews);
		}
	});
}

function afterCheckPossibleNextPageByTop100Comments(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageByTop100Comments();
	} else {
		novigationWithButtonNextPageByTop100Comments();
	}
}

function afterCheckPossibleNextPageForFirstPageByTop100Comments(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageForFirstPageByTop100Comments();
	} else {
		novigationWithButtonNextPageForFirstPageByTop100Comments();
	}
}

function checkPossibleNextPageForFirstPageByAuthor() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageForFirstPageByAuthor(listNews);
		}
	});
}

function checkPossibleNextPageForAllPageByAuthor() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			afterCheckPossibleNextPageByAuthor(listNews);
		}
	});
}

function afterCheckPossibleNextPageByAuthor(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageByAuthor();
	} else {
		novigationWithButtonNextPageByAuthor();
	}
}

function afterCheckPossibleNextPageForFirstPageByAuthor(listNews) {
	var news = listNews[0];
	if (news == null) {
		novigationWithoutButtonNextPageForFirstPageByAuthor();
	} else {
		novigationWithButtonNextPageForFirstPageByAuthor();
	}
}

function doCorrectNews(id) {
	$.ajax({
		url : URL_NEWS + "/" + id + "/?language=" + lang,
		dataType : 'json',
		success : function(news) {
			showUpdateFormNews(news);
		},
		error : function(errorMessage) {
			confirm(errorMessage.responseJSON);
		}
	});
}

function doUpdateNews(news) {
	var idNews = news.id;
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/?language=" + lang,
		type : "PUT",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(news),
		dataType : "json",
		success : function(news) {
			viewSingleNews(idNews);
		},
		error : function(errorMessage) {
			showError(errorMessage);
		}
	});
}

function doViewSingleNews(id) {
	offsetComment = 0;
	limitComment = 20;
	idNews = id;
	$.ajax({
		url : URL_NEWS + "/" + id + "/?language=" + lang,
		dataType : 'json',
		success : function(news) {
			showSingleNews(news);
		},
		error : function(errorMessage) {
			confirm(errorMessage.responseJSON);
		}
	});
}

function doDeleteNews(id) {
	$.ajax({
		url : URL_NEWS + "/" + id + "/?language=" + lang,
		type : "DELETE",
		dataType : "json",
		success : function() {
			$("#news").empty();
			switch (conditionSearch) {
			case "page":
				checkPossiblePage();
				if (possiblePage == true) {
					showAllNews();
					possiblePage = false;
				} else if (offset >= 20) {
					offset = offset - 20;
					showAllNews();
				} else {
					showAllNews();
				}
				break;
			case "tag":
				checkPossiblePage();
				if (possiblePage == true) {
					newsByTag();
					possiblePage = false;
				} else if (offset >= 20) {
					offset = offset - 20;
					newsByTag();
				} else {
					newsByTag();
				}
				break;
			case "author":
				checkPossiblePage();
				if (possiblePage == true) {
					newsByAuthor();
					possiblePage = false;
				} else if (offset >= 20) {
					offset = offset - 20;
					newsByAuthor();
				} else {
					newsByAuthor();
				}
				break;
			case "byComment":
				if (limitNewsByComment == 5) {
					showNewsByTop5Comments()
				} else if (offset >= 20) {
					historyNewsByTop100Comments();
					$(".content").empty();
					getNewsByTop100Comments();
				} else {
					showNewsByTop20Comments()
				}
				break;
			}
		},
		error : function(errorMessage) {
			confirm(errorMessage.responseJSON);
		}
	});
}

function checkPossiblePage() {
	$.ajax({
		url : URL_NEWS + "/?offset=" + offset + "&limit=" + limitNews
				+ "&conditionSearch=" + conditionSearch + "&idAuthor="
				+ idAuthor + "&idTag=" + idTag,
		dataType : 'json',
		success : function(listNews) {
			var news = listNews[0];
			if (news != null) {
				possiblePage = true;
			}
		}
	});
}

function deleteComment(idComment) {
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/comment" + "/" + idComment,
		type : "DELETE",
		success : function() {
			checkPossiblePageComments();
			if (possiblePageComments == true) {
				getAllComments(idNews);
				possiblePageComments = false;
			} else if (offsetComment >= 20) {
				offsetComment = offsetComment - 20;
				getAllComments(idNews);
			} else {
				getAllComments(idNews);
			}
		}
	})
}

function checkPossiblePageComments() {
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/comment" + "/?language=" + lang
				+ "&offset=" + offsetComment + "&limit=" + limitComment,
		dataType : "json",
		success : function(listComments) {
			var comment = listComments[0];
			if (comment != null) {
				possiblePageComments = true;
			}
		}
	})
}

function getAllTags() {
	$.ajax({
		url : URL_TAG,
		dataType : "json",
		success : function(listTags) {
			$.each(listTags, viewAllTags);
		}
	})
}

function getAllAuthors() {
	$.ajax({
		url : URL_AUTHOR,
		dataType : "json",
		success : function(listAuthors) {
			$.each(listAuthors, viewAllAuthors);
		}
	})
}

function getAllTagsForCreateNews() {
	$.ajax({
		url : URL_TAG,
		dataType : "json",
		success : function(listTags) {
			$.each(listTags, viewAllTagsForCreateNews);
		}
	})
}

function getAllAuthorsForCreateNews() {
	$.ajax({
		url : URL_AUTHOR,
		dataType : "json",
		success : function(listAuthors) {
			$.each(listAuthors, viewAllAuthorsForCreateNews);
		}
	})
}

function getAllTagsForUpdateNews() {
	$.ajax({
		url : URL_TAG,
		dataType : "json",
		success : function(listTags) {
			$.each(listTags, viewAllTagsForUpdateNews);
		}
	})
}

function getAllAuthorsForUpdateNews() {
	$.ajax({
		url : URL_AUTHOR,
		dataType : "json",
		success : function(listAuthors) {
			$.each(listAuthors, viewAllAuthorsForUpdateNews);
		}
	})
}

function doAddNews(news) {
	$.ajax({
		url : URL_NEWS + "/?language=" + lang,
		type : "POST",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(news),
		dataType : "json",
		success : function(news) {
			viewSingleNews(news.id);
		},
		error : function(errorMessage) {
			showError(errorMessage);
		}
	});
};

function doAddComment(comment) {
	var idNewsForComment = $("#idNewsForCreateComment").val();
	$.ajax({
		url : URL_NEWS + "/" + idNewsForComment + "/comment" + "/?language="
				+ lang,
		type : "POST",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(comment),
		dataType : "json",
		success : function() {
			$("#textareaComment").val(" ");
			getAllComments();
		}
	})
}

function doAddTag(tag) {
	$.ajax({
		url : URL_TAG,
		type : "POST",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(tag),
		dataType : "json",
		success : function(createdTag) {
			showPageAfterAddTag(createdTag);
		}
	})
}

function doAddAuthor(author) {
	$.ajax({
		url : URL_AUTHOR,
		type : "POST",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(author),
		dataType : "json",
		success : function(createdAuthor) {
			showPageAfterAddAuthor(createdAuthor);
		}
	})
}

function getAllComments() {
	$.ajax({
		url : URL_NEWS + "/" + idNews + "/comment" + "/?language=" + lang
				+ "&offset=" + offsetComment + "&limit=" + limitComment,
		dataType : "json",
		success : function(listComments) {
			if (listComments[0] == null) {
				$(".listComments").empty();
			}
			$.each(listComments, showAllComments);
			novigationComments(listComments);
			changeLang(lang);
		}
	})

}
