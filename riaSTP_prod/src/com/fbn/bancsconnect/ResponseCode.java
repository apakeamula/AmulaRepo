package com.fbn.bancsconnect;

public class ResponseCode {

    public String CheckResponse(String message)
            throws Exception {
        String returnMessage = "";
        if (message != null) {
            int responseCode = Integer.parseInt(message);
            switch (responseCode) {
                case 0:
                    returnMessage = "Transaction Successful";
                    break;
                case 111:
                    returnMessage = "Invalid Scheme Type";
                    break;
                case 114:
                    returnMessage = "Invalid AccountNumber";
                    break;
                case 115:
                    returnMessage = "Invalid Processing Code";
                    break;
                case 116:
                    returnMessage = "Insufficient Fund";
                    break;
                case 119:
                    returnMessage = "Transaction Not Permitted";
                    break;
                case 121:
                    returnMessage = "Withdrawal Amount Limit Exceeded";
                    break;
                case 163:
                    returnMessage = "Invalid Cheque Status";
                    break;
                case 180:
                    returnMessage = "Transfer Limit Exceeded";
                    break;
                case 181:
                    returnMessage = "Cheques are In different books";
                    break;
                case 182:
                    returnMessage = "Not All Cheques could be stopped";
                    break;
                case 183:
                    returnMessage = "Cheque not issued on this account";
                    break;
                case 184:
                    returnMessage = "Requested Block operation failed since Account is closed/frozen";
                    break;
                case 185:
                    returnMessage = "Invalid Transaction Amount/Currency";
                    break;
                case 186:
                    returnMessage = "Block does not Exist";
                    break;
                case 187:
                    returnMessage = "Cheque Stopped";
                    break;
                case 188:
                    returnMessage = "Invalid Rate Currency Combination";
                    break;
                case 189:
                    returnMessage = "Cheque Book already issued";
                    break;
                case 190:
                    returnMessage = "DD already paid";
                    break;
                case 800:
                    returnMessage = "Network Messages was Accepted";
                    break;
                case 902:
                    returnMessage = "Invalid Transaction";
                    break;
                case 904:
                    returnMessage = "Format Error";
                    break;
                case 906:
                    returnMessage = "Cut Over In Progress";
                    break;
                case 907:
                    returnMessage = "Host Not Available";
                    break;
                case 909:
                    returnMessage = "System Malfunction";
                    break;
                case 911:
                    returnMessage = "Host Timed Out";
                    break;
                case 913:
                    returnMessage = "Duplicate Transmission";
                    break;
                default:
                    returnMessage = "Unrecognized Response Code";
            }
        } else {
            throw new Exception("No Response Code Returned, Probably due to Network Failure");
        }
        return returnMessage;
    }
}

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.bancsconnect.ResponseCode

 * JD-Core Version:    0.7.0.1

 */
