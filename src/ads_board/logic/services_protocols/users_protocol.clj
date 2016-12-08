(ns ads-board.logic.services-protocols.users-protocol)

(defprotocol users-service-protocol
	(get-by-email [this email]))