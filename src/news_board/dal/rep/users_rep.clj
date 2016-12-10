(ns news_board.dal.rep.users-rep
	; ;;(:gen-class)
 ;  (:import [java.sql SQLException])
	(:require [news_board.dal.rep-protocol.users-protocol :as users-protocol]
		      [news_board.dal.rep-protocol.base-protocol :as base-protocol]
			  [news_board.dal.dto.user :as user-dto]
			  [clojure.java.jdbc.sql :as sql]
			  [clojure.java.jdbc :as jdbc]
			  [news_board.views :as view]))

; (defmacro try-sql
;   [& args]
;   `(try
;   	(~@args)
;      (catch SQLException e#
;        (view/login))));(jdbc/print-sql-exception-chain e#))))

(deftype users-rep [db-spec]

	;;base-rep-protocol implementaiton
	base-protocol/base-rep-protocol

	(get-items [this] 
		(jdbc/query db-spec 
            ["SELECT id, login, password, firstname, lastname, email FROM user "]
             :row-fn #(user-dto/->user
			 	(:id %1)
			 	(:login %1)
			 	(:password %1)
			 	(:firstname %1)
			 	(:lastname %1)
			 	(:email %1))))

	(get-item [this id]
		(jdbc/query db-spec
             ["SELECT id, login, password, firstname, lastname, email FROM user WHERE id = ?" id]
             :row-fn #(user-dto/->user
			 	(:id %1)
			 	(:login %1)
			 	(:password %1)
			 	(:firstname %1)
			 	(:lastname %1)
			 	(:email %1))))

	(insert-item [this newItem]
		(jdbc/insert! db-spec :user {
				:login (:login newItem) 
				:password (:password newItem)
				:firstname (:firstname newItem)
				:lastname (:lastname newItem)
				:email (:email newItem)}))

	(update-item [this updatedItem] 
		(jdbc/update! db-spec :user{
				:login (:login updatedItem) 
				:password (:password updatedItem) 
				:name (:name updatedItem)
				:last_name (:last_name updatedItem)
				:email (:email updatedItem)}
			["id = ?" (:id updatedItem)]))

	(delete-item [this id]
		(jdbc/delete! db-spec :user ["id = ?" id]))

	;;users-rep-protocol implementation
	users-protocol/users-rep-protocol

	(get-by-email [this email]
		(jdbc/query db-spec 
             ["SELECT id, login, password, firstname, lastname, email FROM user WHERE email = ?" email]
             :row-fn #(user-dto/->user
			 	(:id %1)
			 	(:login %1)
			 	(:password %1)
			 	(:firstname %1)
			 	(:lastname %1)
			 	(:email %1)))))