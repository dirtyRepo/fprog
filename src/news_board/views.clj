(ns news_board.views
	(:use hiccup.page
		  hiccup.element
     :require [news_board.layout :as layout]
     [ring.util.response :as response]))

;; tags
(defn all-tags-page [tag]
	(layout/render
		"tags/tags.html" {:tag tag}))
(defn add-tag-page []
	(layout/render
		"tags/add_tag.html"))

;;users
(defn all-users-page [users deleted added param]
	(layout/render
		"users/all_users.html" {:users users :deleted deleted :added added :param param}))

(defn add-user-page 
  ([] (layout/render "users/add_user.html"))
  ([result u-params]
     (if result 
  		(layout/render "users/add_user.html" (merge {:error-message result :user u-params}))
  	 	(response/redirect "/users"))))

(defn user-page [user updated]
	(layout/render
		"users/user.html" {:user user :updated updated}))

(defn login []
	(layout/render
		"users/login.html"))
;;posts

(defn all-posts-page [posts deleted added param]
	(layout/render
		"posts/all_posts.html" {:posts posts :deleted deleted :added added :param param}))

(defn add-post-page []
	(layout/render
		"posts/add_post.html"))

(defn post-page [post updated]
	(layout/render
		"posts/post.html" {:post post :updated updated}))






