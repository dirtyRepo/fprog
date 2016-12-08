(ns ads-board.dal.rep.feadback-rep
	(:require [ads-board.dal.rep-protocol.feadback-protocol :as feadback-protocol] 
		      [ads-board.dal.rep-protocol.base-protocol :as base-protocol]
			  [ads-board.dal.dto.feadback :as feadback-dto]
			  [clojure.java.jdbc.sql :as sql]
			  [clojure.java.jdbc :as jdbc]))

(deftype feadback-rep [db-spec]

	;;base-rep-protocol implementaiton
	base-protocol/base-rep-protocol

	(get-items [this] 
		(jdbc/query db-spec 
            ["SELECT feadback_id, post_id, user_id, created_at, body FROM feadback"]
             :row-fn #(feadback-dto/->feadback
			 	(:feadback_id %1)
			 	(:post_id %1)
			 	(:user_id %1)
			 	(:created_at %1)
			 	(:body %1))))

	(get-item [this id]
		(jdbc/query db-spec
             ["SELECT feadback_id, post_id, user_id, created_at, body FROM feadback WHERE feadback_id = ?" id]
              :row-fn #(feadback-dto/->feadback
			 	(:feadback_id %1)
			 	(:post_id %1)
			 	(:user_id %1)
			 	(:created_at %1)
			 	(:body %1))))

	(insert-item [this newItem]
		(jdbc/insert! db-spec :feadback { 
				:post_id (:post_id newItem) 
				:created_at (:created_at newItem)
				:body (:body newItem)
				:user_id (:user_id newItem)}))

	(update-item [this updatedItem] 
		(jdbc/update! db-spec :feadback {
				:user_id (:user_id updatedItem) 
				:post_id (:post_id updatedItem) 
				:created_at (:created_at updatedItem)
				:body (:body updatedItem)}
			["feadback_id = ?" (:feadback_id updatedItem)]))

	(delete-item [this id]
		(jdbc/delete! db-spec :feadback ["feadback_id = ?" id]))

	;;feadback-rep-protocol implementation
	feadback-protocol/feadback-rep-protocol

	(get-by-post-id [this post_id]
		(jdbc/query db-spec 
             ["SELECT feadback_id, post_id, user_id, created_at, body FROM feadback WHERE post_id = ?" post_id]
             :row-fn #(feadback-dto/->feadback
			 	(:feadback_id %1)
			 	(:post_id %1)
			 	(:user_id %1)
			 	(:created_at %1)
			 	(:body %1))))
	)