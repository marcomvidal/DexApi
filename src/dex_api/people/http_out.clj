(ns dex-api.people.http-out
  (:require [dex-api.configuration.schemas :as schemas]
            [ring.util.response :as r]
            [schema.core :as s]))

(s/defn respond-people :- {r/response [schemas/Person]}
  [people]
  (r/response people))

(s/defn respond-person :- (schemas/ring-response schemas/Person)
  [person]
  (if (some? person)
    (r/response person)
    (r/not-found {})))

(s/defn respond-empty :- r/response
  []
  (r/response {}))