(ns ads-board.dal.rep.favorite-category-rep
	(:require [ads-board.dal.dto.favorite-category :as favorite-category-dto]
			  [ads-board.dal.rep-protocol.favorite-category-protocol :as favorite-category-protocol] 
			  [clojure.java.jdbc.sql :as sql]
			  [clojure.java.jdbc :as jdbc]))

(deftype favorite-category-rep [db-spec]
	
	;;favorite-category-rep-protocol implementaiton
	favorite-category-protocol/favorite-category-rep-protocol

	(get-by-user-id [this user_id]
		(jdbc/query db-spec 
            ["SELECT category_id, user_id FROM favorite_category WHERE user_id = ?" user_id]
             :row-fn #(favorite-category-dto/->favorite-category
			 	(:category_id %1)
			 	(:user_id %1))))

    (get-by-category-id [this category_id]
		(jdbc/query db-spec 
            ["SELECT category_id, user_id FROM favorite_category WHERE category_id = ?" category_id]
             :row-fn #(favorite-category-dto/->favorite-category
			 	(:category_id %1)
			 	(:user_id %1))))


	(insert-item [this newItem]
		(jdbc/insert! db-spec :favorite_category { 
			    :user_id (:user_id newItem)
				:category_id (:category_id newItem)}))

	(delete-item [this itemToDelete]
		(jdbc/delete! db-spec :favorite_category 
			["category_id = ? and user_id = ?" (:category_id itemToDelete) (:user_id itemToDelete)]))

)