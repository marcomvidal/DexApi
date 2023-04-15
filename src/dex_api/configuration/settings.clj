(ns dex-api.configuration.settings 
  (:require [aero.core :refer [read-config]]))

(def settings
  (read-config "resources/config.edn"))
