(ns ads-board.views
	(:use hiccup.page
		  hiccup.element
     :require [ads-board.layout :as layout]
     [ring.util.response :as response]))

(defn index-page [] 
	(layout/render
    "home.html" {:docs "document"}))


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

;;feadback

(defn all-feadbacks-page [feadback deleted added param]
	(layout/render
		"feadbacks/all_feadbacks.html" {:feadback feadback :deleted deleted :added added :param param}))

(defn add-feadback-page []
	(layout/render
		"feadbacks/add_feadback.html"))

(defn feadback-page [feadback updated]
	(layout/render
		"feadbacks/feadback.html" {:feadback feadback :updated updated}))

;; categories

(defn all-categories-page [category deleted added param]
	(layout/render
		"categories/all_categories.html" {:category category :deleted deleted :added added :param param}))

(defn add-category-page []
	(layout/render
		"categories/add_category.html"))

(defn category-page [category updated]
	(layout/render
		"categories/category.html" {:category category :updated updated}))


