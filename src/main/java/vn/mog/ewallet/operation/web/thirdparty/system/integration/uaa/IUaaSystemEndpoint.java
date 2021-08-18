package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllOccupationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllOccupationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionResponse;

public interface IUaaSystemEndpoint {


  FindLocationResponse getLocation(FindLocationRequest request);

  GetAllOccupationResponse getAllOccupations(GetAllOccupationRequest request);

  GetAllPositionResponse getAllPositions(GetAllPositionRequest request);




}
