(ns dex-api.configuration.reitit.handlers)

;; Substituir prn por uma lib de log
(defn coercion-error-handler [status]
  (fn [exception _]
    (prn "Coercion error" :exception exception :coercion-errors (:errors (ex-data exception)))
    {:status status
     :body (if (= 400 status) {:errors (:errors (ex-data exception))} "Error producing response.")}))

(defn exception-info-handler [exception _request]
  (prn "Server exception:" :exception exception)
  {:status 500
   :body "Internal error."})