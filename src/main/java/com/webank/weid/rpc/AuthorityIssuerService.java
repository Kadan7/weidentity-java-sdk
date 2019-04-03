/*
 *       Copyright© (2018-2019) WeBank Co., Ltd.
 *
 *       This file is part of weidentity-java-sdk.
 *
 *       weidentity-java-sdk is free software: you can redistribute it and/or modify
 *       it under the terms of the GNU Lesser General Public License as published by
 *       the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *
 *       weidentity-java-sdk is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public License
 *       along with weidentity-java-sdk.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.webank.weid.rpc;

import com.webank.weid.protocol.base.AuthorityIssuer;
import com.webank.weid.protocol.base.IssuerDescription;
import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.request.RegisterAuthorityIssuerArgs;
import com.webank.weid.protocol.request.RemoveAuthorityIssuerArgs;
import com.webank.weid.protocol.response.ResponseData;
import java.util.List;

/**
 * Service interface for operations on Authority Issuer.
 *
 * @author chaoxinhu 2018.10
 */
public interface AuthorityIssuerService {

    /**
     * Register a new Authority Issuer on Chain.
     *
     * <p>The input argument actually includes: WeIdentity DID, Name, CreateDate, and Accumulator
     * Value. They will be stored into the 3 fields on the chain: the Bytes32 field (Name); the Int
     * field (create date); the Dynamic Bytes field (accValue). The data Read and Write sequence is
     * fixed in the above mentioned order.
     *
     * @param args the args
     * @return true if succeeds, false otherwise
     */
    ResponseData<Boolean> registerAuthorityIssuer(RegisterAuthorityIssuerArgs args);

    /**
     * Register a new Authority Issuer on Chain by sending preset transaction hex value.
     *
     * @param transactionHex the transaction hex value
     * @return true if succeeds, false otherwise
     */
    ResponseData<String> registerAuthorityIssuer(String transactionHex);

    /**
     * Remove a new Authority Issuer on Chain.
     *
     * @param args the args
     * @return true if succeeds, false otherwise
     */
    ResponseData<Boolean> removeAuthorityIssuer(RemoveAuthorityIssuerArgs args);

    /**
     * Check whether the given WeIdentity DID is an authority issuer, or not.
     *
     * @param weId the WeIdentity DID
     * @return true if yes, false otherwise
     */
    ResponseData<Boolean> isAuthorityIssuer(String weId);

    /**
     * Query the authority issuer information from a given WeIdentity DID.
     *
     * @param weId the WeIdentity DID
     * @return authority issuer info
     */
    ResponseData<AuthorityIssuer> queryAuthorityIssuerInfo(String weId);

    /**
     * Get all of the authority issuer.
     *
     * @param index start position
     * @param num number of returned authority issuer in this request
     * @return Execution result
     */
    ResponseData<List<AuthorityIssuer>> getAllAuthorityIssuerList(Integer index, Integer num);

    /**
     * Register a new issuer type.
     *
     * @param issuerType the specified issuer type
     * @return Execution result
     */
    ResponseData<Boolean> registerIssuerType(String issuerType);

    /**
     * Marked an issuer as the specified issuer type.
     *
     * @param callerAuth the caller who have the access to modify this list
     * @param issuerType the specified issuer type
     * @param targetIssuerWeId the weId of the issuer who will be marked as a specific issuer type
     * @return Execution result
     */
    ResponseData<Boolean> addIssuerIntoIssuerType(
        WeIdAuthentication callerAuth,
        String issuerType,
        String targetIssuerWeId
    );

    /**
     * Removed an issuer from the specified issuer list.
     *
     * @param callerAuthWrapper the caller who have the access to modify this list
     * @param issuerType the specified issuer type
     * @param targetIssuerWeId the weId of the issuer who will be removed from a specific issuer
     * type list
     * @return Execution result
     */
    ResponseData<Boolean> removeIssuerFromIssuerType(
        WeIdAuthentication callerAuthWrapper,
        String issuerType,
        String targetIssuerWeId
    );

    ResponseData<Boolean> isSpecificTypeIssuer(
        String issuerType,
        String targetIssuerWeId
    );

    ResponseData<List<IssuerDescription>> getAllSpecificTypeIssuerList(
        String issuerType,
        Integer index,
        Integer num
    );
}
