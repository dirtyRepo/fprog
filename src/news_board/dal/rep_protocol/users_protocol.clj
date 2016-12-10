(ns news_board.dal.rep-protocol.users-protocol)

(defprotocol users-rep-protocol
	(get-by-email [this email]))