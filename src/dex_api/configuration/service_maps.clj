(ns dex-api.configuration.service-maps
  (:require [dex-api.configuration.headers :as headers]
            [dex-api.configuration.reitit.configuration :as reitit-config]
            [dex-api.interceptors.component :refer [component-interceptor]]
            [io.pedestal.http :as http]
            [dex-api.routes :refer [routes]]
            [reitit.pedestal :as pedestal]))

(def base
  {:env :prod
   ::http/allowed-origins ["scheme://host:port"]
   ::http/resource-path "/public"
   ::http/type :jetty
   ::http/host "0.0.0.0"
   ::http/port 8080
   ::http/secure-headers headers/csp
   ::http/routes []})

(defn dev
  []
  (println "\nCreating your [DEV] server...")
  (-> base
      (merge {:env :dev
              ::http/join? false
              ::http/allowed-origins {:creds true
                                      :allowed-origins (constantly true)}})
      http/dev-interceptors))

(defn common
  [components service-map]
  (-> service-map
      http/default-interceptors
      (pedestal/replace-last-interceptor (reitit-config/setup routes))
      (update ::http/interceptors conj (component-interceptor components))))