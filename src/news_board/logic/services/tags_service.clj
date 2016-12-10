(ns news_board.logic.services.tags-service
  ;;(:gen-class)
  (:import [java.lang Exception])
  (:require [news_board.logic.services-protocols.base-protocol :as base-protocol]
            [news_board.logic.services-protocols.tags-protocol :as tags-protocol]
            [news_board.dal.rep.tags-rep :as tags-repo]
            [news_board.views :as view]))

(defmacro try-request [& args]
  `(try (~@args)
        (catch Exception e#
          (.getMessage e#))))

(deftype tags-service [tags-repo]

  base-protocol/base-service-protocol

  (get-items [this]
    (.get-items tags-repo))

  (get-item [this id]
    (first (.get-item tags-repo id)))

  (insert-item [this newItem]
    (try
      (.insert-item tags-repo newItem)
      nil
      (catch Exception e#
        (.getMessage e#))))

  (update-item [this updatedItem]
    (.update-item tags-repo updatedItem))

  (delete-item [this id]
    (.delete-item tags-repo id))

  tags-protocol/tags-service-protocol)
