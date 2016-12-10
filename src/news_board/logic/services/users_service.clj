(ns news_board.logic.services.users-service
	;;(:gen-class)
  (:import [java.lang Exception])
	(:require [news_board.logic.services-protocols.base-protocol :as base-protocol]
			  [news_board.logic.services-protocols.users-protocol :as users-protocol]
			  [news_board.dal.rep.users-rep :as users-repo]
			  [news_board.views :as view]))

(defmacro try-request [& args]
  `(try (~@args)
     (catch Exception e#
       (.getMessage e#))))

(deftype users-service [users-repo] 

	base-protocol/base-service-protocol

	(get-items [this] 
		(.get-items users-repo))

	(get-item [this id]
		(first (.get-item users-repo id)))

	(insert-item [this newItem]
		(try 
			(.insert-item users-repo newItem)
			nil
		(catch Exception e#
        (.getMessage e#))))

	(update-item [this updatedItem]
		(.update-item users-repo updatedItem))

	(delete-item [this id]
		(.delete-item users-repo id))

	users-protocol/users-service-protocol

	(get-by-email [this email]
		(.get-by-email users-repo email)))
