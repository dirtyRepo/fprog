(ns ads-board.logic.services.feadback-service
	(:require [ads-board.logic.services-protocols.base-protocol :as base-protocol]
			  [ads-board.logic.services-protocols.feadback-protocol :as feadback-protocol]
			  [ads-board.dal.rep.feadback-rep :as feadback-repo]))

(deftype feadback-service [feadback-repo]

	base-protocol/base-service-protocol

	(get-items [this]
		(.get-items feadback-repo))

	(get-item [this id]
		(first (.get-item feadback-repo id)))

	(insert-item [this newItem]
		(.insert-item feadback-repo newItem))

	(update-item [this updatedItem]
		(.update-item feadback-repo updatedItem))

	(delete-item [this id]
		(.delete-item feadback-repo id))

	feadback-protocol/feadback-service-protocol

	(get-by-post-id [this post_id]
		(.get-by-post-id feadback-repo post_id)))
