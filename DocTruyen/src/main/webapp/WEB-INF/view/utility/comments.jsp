<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="comment_box_content">
	<div class="comment_box_inner_content">
		<c:forEach var="comment" items="${comments}">
			<div id="${comment.id}" data-comment-id="${comment.id}"
				data-user-id="${comment.userId}"
				class="comment
                <c:if test="${not empty sessionScope.user 
                             and comment.userId == sessionScope.user.id}">
                    yourComment
                </c:if>
            ">
				<div class="comment_author_and_content">
					<div class="comment_author">
						<span>${comment.username}</span>
					</div>

					<div class="comment_content">
						<p>
							<c:if test="${comment.parentCommentId != null}">
								<a class="reply-comment" href = "#${comment.parentCommentId}"> <span class="reply-comment-id">
										^${comment.parentCommentId} </span> <span
									class="reply-comment-username">
										${comment.parentUsername} </span>
								</a>
							</c:if>

							${comment.content}
						</p>
					</div>

				</div>

				<div class="comment_information">
					<span> <c:out value="${comment.timeAgo}" />
					</span> <span class="mx-1">·</span> <span> <span
						class="text-gray-700">^</span> <c:out value="${comment.id}" />
					</span> <span class="mx-1">·</span>

					<c:choose>
						<c:when
							test="${not empty sessionScope.user 
                                       and comment.userId == sessionScope.user.id}">
							<button class="remove_button" data-comment-id="${comment.id}">
								Gỡ bỏ</button>
						</c:when>
						<c:otherwise>
							<button class="reply_button">Trả lời</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
