(ns news_board.dal.rep.tags-rep
  (:require [news_board.dal.rep-protocol.tags-protocol :as tags-protocol]
            [news_board.dal.rep-protocol.base-protocol :as base-protocol]
            [news_board.dal.dto.tag :as tag-dto]
            [clojure.java.jdbc.sql :as sql]
            [clojure.java.jdbc :as jdbc]
            [news_board.views :as view]))


(deftype tags-repo [db-spec]

  base-protocol/base-rep-protocol

  (get-items [this]
    (jdbc/query db-spec
                ["SELECT id, name, subscribers_count FROM tag"]
                :row-fn #(tag-dto/->tag
                          (:id %1)
                          (:name %1)
                          (:subscribers_count %1))))

  (get-item [this id]
    (jdbc/query db-spec
                ["SELECT id, name, subscribers_count FROM tag WHERE id = ?" id]
                :row-fn #(tag-dto/->tag
                          (:id %1)
                          (:name %1)
                          (:subscribers_count %1))))

  (insert-item [this newItem]
    (jdbc/insert! db-spec :tag {
      :name (:name newItem)}))

  tags-protocol/tags-rep-protocol

  (get-users-subsribe-tags-id [this id]
    (jdbc/query db-spec
                ["SELECT id_tag FROM tag_user WHERE id_tag = ?" id]))

  )