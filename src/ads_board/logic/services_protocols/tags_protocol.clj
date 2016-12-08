(ns ads-board.logic.services-protocols.tags-protocol)

(defprotocol tags-service-protocol
  (get-by-email [this name]))