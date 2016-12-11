(ns news_board.handler
  (:use compojure.core)
  (:require [ring.util.response :as response]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.java.io :as io]
            [ring.util.response :as resp]
            [news_board.layout :as layout]
            [news_board.views :as view]

            [news_board.dal.db-conf :as db]

            ;;import for users

            [news_board.logic.services.users-service :as users-service]
            [news_board.dal.dto.user :as user]
            [news_board.dal.rep.users-rep :as users-repo]



             ;;import for posts

            [news_board.logic.services.posts-service :as posts-service]
            [news_board.dal.dto.post :as post]
            [news_board.dal.rep.posts-rep :as posts-repo]

            [news_board.logic.services.tags-service :as tags-service]
            [news_board.dal.dto.tag :as tag]
            [news_board.dal.rep.tags-rep :as tags-repo]

            [news_board.dal.dto.like :as like]
            [news_board.dal.rep.likes-rep :as likes-repo]
            [news_board.logic.services.likes-service :as likes-service]
            ))





(def users-repository (users-repo/->users-rep db/db-spec))
(def users-service (users-service/->users-service users-repository))

(def posts-repository (posts-repo/->posts-rep db/db-spec))
(def posts-service (posts-service/->posts-service posts-repository))

(def likes-repository (likes-repo/->like-repo db/db-spec))
(def likes-service (likes-service/->likes-service likes-repository) )

(def tags-repository (tags-repo/->tags-repo db/db-spec))
(def tags-service (tags-service/->tags-service tags-repository))
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


  (GET "/tags" [id] (view/all-tags-page (.get-items tags-service)))

  (GET "/tag/add" [] (view/add-tag-page))

  (POST "/tag/add" request (do (.insert-item tags-service (tag/->tag
              nil
              (get-in request [:params :name])
              nil))
              (response/redirect "/tags")))

  (POST "/like/add" request (do (.insert-item likes-service (like/->like
                                                           (get-in request [:id_user :id_user])
                                                           (get-in request [:id_post :id_post]))
                               (response/redirect "/"))))



  (GET "/user_add" [] (view/add-likes-tasg))


  (GET "/" [] (layout/render
    "base.html" {:docs "document"}))

  (route/not-found "Not Found"))

; (def app
;   (wrap-defaults app-routes site-defaults))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))