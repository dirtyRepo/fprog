(ns ads-board.dal.rep.posts-rep
  (:require [ads-board.dal.rep-protocol.posts-protocol :as posts-protocol]
            [ads-board.dal.rep-protocol.base-protocol :as base-protocol]
            [ads-board.dal.dto.post :as post-dto]
            [clojure.java.jdbc.sql :as sql]
            [clojure.java.jdbc :as jdbc]))
(use '[clojure.string :only (split triml)])
(deftype posts-rep [db-spec]

  ;;base-rep-protocol implementaiton
  base-protocol/base-rep-protocol

  (get-items [this]
    (jdbc/query db-spec
                ["SELECT id, author, title, description FROM post"]
                :row-fn #(post-dto/->post
                          (:id %1)
                          (:author %1)
                          (:title %1)
                          (:description %1)
                          (:tags %1))))

  (get-item [this id]
    (jdbc/query db-spec
                ["SELECT id, author, title, description FROM post WHERE id = ?" id]
                :row-fn #(post-dto/->post
                          (:id %1)
                          (:author %1)
                          (:title %1)
                          (:description %1)
                          (:tags %1))))

  (insert-item [this newItem]
    (jdbc/insert! db-spec :post {
                                 :title       (:title newItem)
                                 :description (:description newItem)
                                 :author      (:author newItem)})
    (doseq [tag-name [(split (:tags newItem) #"\s+" )]]
      (println "insert ignore into post_tag (id_post,id_tag) values (?,(select id from tag where tag.name= ?))" (:id newItem) tag-name )
      (jdbc/query db-spec ["insert ignore into post_tag (id_post,id_tag) values (?,(select id from tag where tag.name= ?))" (:id newItem) tag-name]
                                        ))))
;
;(update-item [this updatedItem]
;	(jdbc/update! db-spec :post{
;			:author (:author updatedItem)
;			:title (:title updatedItem)
;			:description (:description updatedItem)
;			:tags (:tags updatedItem)}
;		["id = ?" (:id updatedItem)]))
;
;(delete-item [this id]
;	(jdbc/delete! db-spec :post ["id = ?" id]))
;
;;;post-rep-protocol implementation
;post-protocol/post-rep-protocol
;
;(get-by-user-id [this author]
;	(jdbc/query db-spec
;          ["SELECT id, author, category_id, title, description, tags, created_at, updated_at FROM post WHERE author = ?" author]
;          :row-fn #(post-dto/->post
;		 	(:id %1)
;		 	(:author %1)
;		 	(:title %1)
;		 	(:description %1)
;		 	(:tags %1))))