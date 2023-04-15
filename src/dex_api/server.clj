(ns dex-api.server
  (:gen-class) ; for -main method in uberjar
  (:require [com.stuartsierra.component :as component]
            [dex-api.components.system :as system]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (component/start-system (system/build :prod)))

(comment
  (def app (component/start-system (system/build :dev)))
  (component/stop-system app))
