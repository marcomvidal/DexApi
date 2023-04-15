(ns dex-api.configuration.schemas
  (:require [schema.core :as s]))

(def Unit (s/=> (s/eq nil)))
(def DateTime (s/=> (s/eq org.joda.time.DateTime)))

(s/defschema Person 
  {:name s/Str
   :email s/Str
   :birthdate DateTime})

(s/defschema PersonResponse
  (merge Person {:id s/Int
                 :birthdate java.util.Date}))

(s/defschema NextJdbcExecuteOperation
  {:update-count s/Int})

(defn ring-response
  [schema]
  {:status s/Int
   :headers {s/Str s/Str}
   :body schema})
