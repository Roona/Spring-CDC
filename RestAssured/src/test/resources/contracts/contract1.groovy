import org.springframework.cloud.contract.spec.Contract
Contract.make{
request{
url '/api/hello'
method 'GET'
}
response{
status 200
}
}