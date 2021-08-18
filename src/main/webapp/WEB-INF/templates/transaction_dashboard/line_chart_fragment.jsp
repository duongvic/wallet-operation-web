<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<ul class="nav nav-tabs">
    <li role="presentation" onclick="setActiveLineTbl('numOfTxns')" id="numOfTxns" class="active"><a>Transactions</a></li>
    <li role="presentation" onclick="setActiveLineTbl('sumReqAmount')" id="sumReqAmount"><a>TPV</a></li>
    <li role="presentation" onclick="setActiveLineTbl('sumRevenue')" id="sumRevenue"><a>Revenue</a></li>
</ul>

<canvas  id="numOfTxns_tbl" style="width: 100%; max-height: 300px;"></canvas>
<canvas  id="sumReqAmount_tbl" style="width: 100%; max-height: 300px; display: none"></canvas>
<canvas  id="sumRevenue_tbl" style="width: 100%; max-height: 300px; display: none"></canvas>

