(ns dex-api.configuration.reitit.interceptors 
  (:require [dex-api.configuration.reitit.handlers :as handlers]
            [reitit.http.coercion :as coercion]
            [reitit.http.interceptors.exception :as exception]
            [reitit.http.interceptors.muuntaja :as muuntaja]
            [reitit.http.interceptors.parameters :as parameters]))

(def interceptors
  [;; Query-params & form-params
   (parameters/parameters-interceptor)
   ;; Content-negotiation
   (muuntaja/format-negotiate-interceptor)
   ;; Encoding response body
   (muuntaja/format-response-interceptor)
   ;; Exception handling
   (exception/exception-interceptor
    (merge
     exception/default-handlers
     {:reitit.coercion/request-coercion (handlers/coercion-error-handler 400)
      :reitit.coercion/response-coercion (handlers/coercion-error-handler 500)
      clojure.lang.ExceptionInfo handlers/exception-info-handler}))
   ;; Decoding request body
   (muuntaja/format-request-interceptor)
   ;; Handle errors from helpers
   (coercion/coerce-response-interceptor)
   ;; Coercing request parameters
   (coercion/coerce-request-interceptor)])
