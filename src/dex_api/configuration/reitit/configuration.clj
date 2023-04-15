(ns dex-api.configuration.reitit.configuration
  (:require [dex-api.configuration.reitit.interceptors :as i]
            [muuntaja.core :as m]
            [reitit.coercion.schema :as schema]
            [reitit.http :as http]
            [reitit.pedestal :as pedestal]
            [reitit.ring :as ring]))

(def options
  {:data {:coercion schema/coercion
          :muuntaja (m/create
                     (assoc-in
                      m/default-options
                      [:formats "application/json" :decoder-opts :bigdecimals]
                      true))
          :interceptors i/interceptors}})

(defn setup
  [routes]
   (pedestal/routing-interceptor
    (http/router routes options)
    (ring/routes (ring/create-default-handler))))
