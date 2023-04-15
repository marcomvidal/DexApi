(ns dex-api.components.system
  (:require [com.stuartsierra.component :as component]
            [dex-api.components.database :refer [new-database]]
            [dex-api.components.server :refer [new-server]]
            [dex-api.components.service-map :refer [new-service-map]]
            [dex-api.configuration.settings :refer [settings]]))

(defn build
  [env]
  (component/system-map
   :database (new-database (:database settings))
   :service-map (component/using (new-service-map env) {:db :database})
   :server (component/using (new-server) {:service :service-map})))
