(ns dex-api.components.service-map
  (:require [com.stuartsierra.component :as component]
            [dex-api.configuration.service-maps :as service-maps]
            [io.pedestal.http :as http]))

(defrecord ServiceMap [env db]
  component/Lifecycle
  
  (start [this]
    (println ";; Starting service-map")
    (->> (if (= env :dev) (service-maps/dev) service-maps/base)
         (service-maps/common {:database db})
         http/create-server
         (assoc this :map))) 
  
  (stop [this] 
    (println ";; Stopping service-map")
    (assoc this :map nil)))

(defn new-service-map
  [env]
  (map->ServiceMap {:env env}))
