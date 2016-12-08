(ns ads-board.dal.rep.favorite-post-rep
	(:require [ads-board.dal.dto.favorite-post :as favorite-post-dto]
			  [ads-board.dal.rep-protocol.favorite-post-protocol :as favorite-post-protocol] 
			  [clojure.java.jdbc.sql :as sql]
			  [clojure.java.jdbc :as jdbc]))

(deftype favorite-post-rep [db-spec]

	;;favorite-category-rep-protocol implementaiton
	favorite-post-protocol/favorite-post-rep-protocol

	(get-by-user-id [this user_id]
		(jdbc/query db-spec 
            ["SELECT post_id, user_id FROM favorite_post WHERE user_id = ?" user_id]
             :row-fn #(favorite-post-dto/->favorite-post
			 	(:post_id %1)
			 	(:user_id %1))))

    (get-by-post-id [this post_id]
		(jdbc/query db-spec 
            ["SELECT post_id, user_id FROM favorite_post WHERE post_id = ?" post_id]
             :row-fn #(favorite-post-dto/->favorite-post
			 	(:post_id %1)
			 	(:user_id %1))))


	(insert-item [this newItem]
		(jdbc/insert! db-spec :favorite_post { 
			    :user_id (:user_id newItem)
				:post_id (:post_id newItem)}))

	(delete-item [this itemToDelete]
		(jdbc/delete! db-spec :favorite_post
			["post_id = ? and user_id = ?" (:post_id itemToDelete) (:user_id itemToDelete)]))

)