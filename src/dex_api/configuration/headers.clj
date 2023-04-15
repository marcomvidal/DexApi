(ns dex-api.configuration.headers)

(def csp
  {:content-security-policy-settings {:default-src "'self'"
                                      :style-src "'self' 'unsafe-inline'"
                                      :script-src "'self' 'unsafe-inline'"
                                      :img-src "'self' 'unsafe-inline' data: https://validator.swagger.io"
                                      :object-src "'none'"}})