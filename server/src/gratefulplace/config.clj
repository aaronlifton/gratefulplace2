(ns gratefulplace.config
  (:require [gratefulplace.utils :refer :all]
            [environ.core :refer :all]))

(def conf
  (let [environment (or (env :app-env) "development")]
    (merge-with
     merge
     {:html-paths ["html-app"
                   "../html-app/app"
                   "../html-app/.tmp"]
      :moderator-names ["flyingmachine"]
      :send-email false
      :email {:host "smtp.gmail.com"
              :from-address "notifications@gratefulplace.com"
              :from-name "Grateful Place Notifications"}}
     {:email {:username (env :gp-email-username)
              :password (env :gp-email-password)}}
     (read-resource (str "config/environments/" environment ".edn")))))

(defn config
  [& keys]
  (get-in conf keys))