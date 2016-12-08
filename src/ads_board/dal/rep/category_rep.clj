(ns ads-board.dal.rep.category-rep
	(:require [ads-board.dal.rep-protocol.base-protocol :as base-protocol]
			  [ads-board.dal.dto.category :as category-dto]
			  [clojure.java.jdbc.sql :as sql]
			  [clojure.java.jdbc :as jdbc]))

(deftype category-rep [db-spec]

	;;base-rep-protocol implementaiton
	base-protocol/base-rep-protocol

	(get-items [this] 
		(jdbc/query db-spec 
            ["SELECT category_id, c_name FROM category"]
             :row-fn #(category-dto/->category
			 	(:category_id %1)
			 	(:c_name %1))))

	(get-item [this id]
		(jdbc/query db-spec
             ["SELECT category_id, c_name FROM category WHERE category_id = ?" id]
              :row-fn #(category-dto/->category
			 	(:category_id %1)
			 	(:c_name %1))))

	(insert-item [this newItem]
		(jdbc/insert! db-spec :category { 
				:c_name (:c_name newItem)}))

	(update-item [this updatedItem] 
		(jdbc/update! db-spec :category {
				:c_name (:c_name updatedItem)}
			["category_id = ?" (:category_id updatedItem)]))

	(delete-item [this id]
		(jdbc/delete! db-spec :category ["category_id = ?" id]))

)