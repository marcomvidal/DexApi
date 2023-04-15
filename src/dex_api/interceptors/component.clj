(ns dex-api.interceptors.component 
  (:require [io.pedestal.interceptor :as i]))

(defn component-interceptor
  [components]
  (i/interceptor
   {:name (i/interceptor-name ::system-component-interceptor)
    :enter #(update % :request assoc :components components)
    :leave #(update % :request assoc :components "<service-components>")
    :error (fn [context exception]
             (-> context
                 (assoc-in [:request ::components] "<service-components>")
                 (assoc :io.pedestal.interceptor.chain/error exception)))}))
