(ns news_board.logic.services-protocols.users-protocol)

(defprotocol users-service-protocol
	(get-by-email [this email]))