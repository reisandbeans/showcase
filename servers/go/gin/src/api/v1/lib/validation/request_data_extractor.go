package validation

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"io"
)

type RequestData struct {
	Body   gin.H `json:"body"`
	Params gin.H `json:"params"`
	Query  gin.H `json:"query"`
}

func GetRequestData(c *gin.Context) RequestData {
	var body gin.H
	var bodyData, _ = io.ReadAll(c.Request.Body)
	json.Unmarshal(bodyData, &body)

	var query gin.H
	var queryData = c.Request.URL.Query()
	for key, values := range queryData {
		query[key] = values
	}

	var params gin.H
	var paramsData = c.Params
	for _, param := range paramsData {
		params[param.Key] = param.Value
	}

	requestData := RequestData{
		Body:   body,
		Params: params,
		Query:  query,
	}

	return requestData
}
