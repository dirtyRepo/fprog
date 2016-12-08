(ns ads-board.dal.rep.tags-rep
  (:require [ads-board.dal.rep-protocol.users-protocol :as users-protocol]
            [ads-board.dal.rep-protocol.base-protocol :as base-protocol]
            [ads-board.dal.dto.tag :as tag-dto]
            [clojure.java.jdbc.sql :as sql]
            [clojure.java.jdbc :as jdbc]
            [ads-board.views :as view]))


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

  )