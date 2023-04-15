(ns dex-api.people.controller
  (:require [dex-api.configuration.schemas :as schemas]
            [dex-api.people.adapters :as adapters]
            [dex-api.people.http-out :as http-out]
            [dex-api.people.repository :as repository]
            [ring.util.response :refer [response]]
            [schema.core :as s]))

(s/defn index :- {response [schemas/Person]}
  [{:keys [database]}]
  (->> database
       repository/get-all!
       adapters/get-all!->http-out
       http-out/respond-people))

(s/defn save :- (schemas/ring-response schemas/Person)
  [database person] 
  (repository/save! database person)
  (http-out/respond-person person))

(s/defn by-email :- {response schemas/Person}
  [database email]
  (->> email
       (repository/get-by-email! database)
       http-out/respond-person))

(s/defn delete :- response
  [database email]
  (repository/delete-by-email! database email)
  (http-out/respond-empty))
