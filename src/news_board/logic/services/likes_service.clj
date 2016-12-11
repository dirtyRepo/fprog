(ns news_board.logic.services.likes-service
  (:require [news_board.logic.services-protocols.base-protocol :as base-protocol]
            [news_board.dal.rep.likes-rep :as like-repo]))

(defn now [] (new java.util.Date))

(deftype likes-service [like-repo]

  base-protocol/base-service-protocol

  (get-items [this]
    (.get-items like-repo))

  (get-item [this id]
    (first (.get-item like-repo id)))

  (insert-item [this newItem]
    (.insert-item like-repo newItem))

  (update-item [this updatedItem]
    (.update-item like-repo))

  (delete-item [this id]
    (.delete-item like-repo id)))