(ns ads-board.handler
  (:use compojure.core)
  (:require [ring.util.response :as response]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.java.io :as io]
            [ring.util.response :as resp]
            [ads-board.layout :as layout]
            [ads-board.views :as view]

            [ads-board.dal.db-conf :as db]

            ;;import for users

            [ads-board.logic.services.users-service :as users-service]
            [ads-board.dal.dto.user :as user]
            [ads-board.dal.rep.users-rep :as users-repo]
    

            ;;import for like

            [ads-board.logic.services.likes-service :as like-service]
            [ads-board.dal.dto.like :as like]
            [ads-board.dal.rep.likes-rep :as like-repo]


             ;;import for posts

            [ads-board.logic.services.posts-service :as posts-service]
            [ads-board.dal.dto.post :as post]
            [ads-board.dal.rep.posts-rep :as posts-repo]

            [ads-board.logic.services.tags-service :as tags-service]
            [ads-board.dal.dto.tag :as tag]
            [ads-board.dal.rep.tags-rep :as tags-repo]


            ))





(def users-repository (users-repo/->users-rep db/db-spec))
(def users-service (users-service/->users-service users-repository))

(def posts-repository (posts-repo/->posts-rep db/db-spec))
(def posts-service (posts-service/->posts-service posts-repository))

(def like-repository (like-repo/->like-rep db/db-spec))
(def like-service (like-service/->like-service like-repository))


(def tags-repository (tags-repo/->tags-repo db/db-spec))
(def tags-service (tags-service/->tags-service tags-repository))

(defn create-user ([login password name lastname birth_date email address phone] (user/->user nil login password name lastname email ))
          ([id login password name lastname birth_date email address phone] (user/->user id login password name lastname email)))

(defn create-post ([id category_id title description status created_at updated_at] (post/->post nil id category_id title description status created_at updated_at))
          ([id id category_id title description status created_at updated_at] (post/->post id id category_id title description status created_at updated_at)))

(defroutes app-routes


  ;; users
  (GET "/users" [] (view/all-users-page (.get-items users-service) false false nil))

  (GET "/user/add" [] (view/add-user-page))

  (POST "/user/add" request (do
    (def user-params (user/->user
                        nil
                        (get-in request [:params :login])
                        (get-in request [:params :password])
                        (get-in request [:params :firstname])
                        (get-in request [:params :lastname])
                        (get-in request [:params :email]))))
                        ( def result (.insert-item users-service user-params))
                        (.println System/out (:login user-params))
                   (view/add-user-page (merge result) user-params))

  (POST "/user/update" request (do (.update-item users-service (user/->user
                        (get-in request [:params :id])
                        (get-in request [:params :login])
                        (get-in request [:params :password])
                        (get-in request [:params :firstname])
                        (get-in request [:params :lastname])
                        (get-in request [:params :email])))
                  (response/redirect "/users")))

  (POST "/user/delete" request (do (.delete-item users-service
                        (get-in request [:params :id]))
                 (response/redirect "/users")))

  (GET "/user/:id" [id] (view/user-page (.get-item users-service id) false))

  (GET "/login" [] (view/login))

  (GET "/posts" [] (view/all-posts-page (.get-items posts-service) false false nil))

  (GET "/post/add" [] (view/add-post-page))

  (POST "/post/add" request (do (.insert-item posts-service (post/->post
                  nil
                  (get-in request [:params :author])
                  (get-in request [:params :title])
                  (get-in request [:params :description])
                  (get-in request [:params :tags])))
                  (response/redirect "/posts")))

  (POST "/post/update" request (do (.update-item posts-service (post/->post
                  (get-in request [:params :post_id])
                  (get-in request [:params :id])
                  (get-in request [:params :title])
                  (get-in request [:params :status])
                  (get-in request [:params :description])))
                  (response/redirect "/posts")))

  (POST "/post/delete" request (do (.delete-item posts-service
                        (get-in request [:params :post_id]))
                 (response/redirect "/posts")))

  (GET "/post/:id" [id] (view/post-page (.get-item posts-service id) false))

  ;;comments

  (GET "/like" [] (view/all-likes-page (.get-items like-service) false false nil))

  (GET "/like/add" [] (view/add-like-page))

  (POST "/like/add" request (do (.insert-item like-service (like/->like
                        (get-in request [:params :post_id])
                        (get-in request [:params :user_id])))
                                (print :post_id :user_id)
                  (response/redirect "/like")))

  (POST "/like/update" request (do (.update-item like-service (like/->like
                        (get-in request [:params :post_id])
                        (get-in request [:params :user_id])))
                  (response/redirect "/like")))

  (POST "/like/delete" request (do (.delete-item like-service
                        (get-in request [:params :like_id]))
                 (response/redirect "/like")))

  (GET "/like/:id" [id] (view/like-page (.get-item like-service id) false))


  (GET "/tags" [id] (view/all-tags-page (.get-items tags-service)))

  (GET "/tag/add" [] (view/add-tag-page))

  (POST "/tag/add" request (do (.insert-item tags-service (tag/->tag
              nil
              (get-in request [:params :name])
              nil))
              (response/redirect "/tags")))



  (GET "/" [] (layout/render
    "home.html" {:docs "document"}))

  (route/not-found "Not Found"))

; (def app
;   (wrap-defaults app-routes site-defaults))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))