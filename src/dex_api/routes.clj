(ns dex-api.routes
  (:require [dex-api.people.http-in :as http-in]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [schema.core :as s]
            [dex-api.configuration.schemas :as schemas]))

(def routes
  [["/people" {:get {:summary "Displays all people registered."
                     :handler http-in/index
                     :responses {200 {:body [schemas/PersonResponse]}}}
               :post {:summary "Registers a new person."
                      :handler http-in/save}}]
   ["/people/:email" {:get {:summary "Fetches person information by its e-mail."
                            :parameters {:path {:email s/Str}}
                            :responses {200 {:body {:id s/Int
                                                    :name s/Str
                                                    :email s/Str
                                                    :birthdate s/Str}}}
                            :handler http-in/by-email}
                      :delete {:summary "Deletes a person by its e-mail."
                               :parameters {:path {:email s/Str}}
                               :handler http-in/delete}}]
   ["" {:no-doc true}
    ["/swagger.json" {:get (swagger/create-swagger-handler)}]
    ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]])
