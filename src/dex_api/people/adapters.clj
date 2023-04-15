(ns dex-api.people.adapters
  (:require [clj-time.format :as f]
            [clj-time.coerce :as coerce]))

(defn get-all!->http-out
  [people]
  (->> people
       (map (fn [{:keys [birthdate] :as person}]
              (merge person {:birthdate (coerce/to-date
                                         (f/parse-local-date
                                          (f/formatter "dd/MM/yyyy")
                                          birthdate))})))))
