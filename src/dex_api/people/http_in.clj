(ns dex-api.people.http-in
  (:require [dex-api.configuration.schemas :as schemas]
            [dex-api.people.controller :as controller]
            [ring.util.response :refer [response]]
            [schema.core :as s]))

(s/defn index :- {response [schemas/Person]}
  [{:keys [components]}]
  (controller/index components))

(s/defn save :- (schemas/ring-response schemas/Person)
  [{:keys [components] :as request}]
  (controller/save
   (:database components)
   (:body-params request)))

(s/defn by-email :- {response schemas/Person}
  [{:keys [components] :as request}]
  (controller/by-email
   (:database components)
   (get-in request [:path-params :email])))

(s/defn delete :- response
  [{:keys [components] :as request}]
  (controller/delete
   (:database components)
   (get-in request [:path-params :email])))
