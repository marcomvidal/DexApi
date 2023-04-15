(ns dex-api.components.server
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]))

(defrecord Server [service]
  component/Lifecycle

  (start [this]
    (println ";; Starting server") 
    (->> (:map service)
         http/start
         (assoc this :jetty)))

  (stop [this]
    (println ";; Stopping server")
    (when-let [jetty (:jetty this)]
      (http/stop jetty))
    (assoc this :jetty nil)))

(defn new-server
  []
  (map->Server {}))
