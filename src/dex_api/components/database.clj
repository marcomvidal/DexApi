(ns dex-api.components.database
  (:require [com.stuartsierra.component :as component]
            [dex-api.configuration.database :as database]
            [next.jdbc :as jdbc]))

(defrecord Database [settings]
  component/Lifecycle

  (start [this]
    (println ";; Starting database")
    (->> settings
         database/connection-pool
         (assoc this :connection)))

  (stop [this]
    (println ";; Stopping database")
    (->> (:connection this)
         jdbc/get-connection
         .close)
    (assoc this :connection nil)))

(defn new-database
  [settings]
  (map->Database {:settings settings}))
