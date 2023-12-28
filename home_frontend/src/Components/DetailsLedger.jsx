import React, {useState} from "react";

function DetailsLedger(){
    
    const [isLedgPop, setLedgPop] = useState(false);

    function handleLedgPop(){
        setLedgPop(!isLedgPop);
    }

    const [isNewLedgPop, setNewLedgPop] = useState(false);

    function handleNewLedgPop(){
        setNewLedgPop(!isNewLedgPop);
    }
    
    return (
        <div className="ledger detailsLedger">
                            <div className="detailsLedgerHeader">
                                <h2>LEDGERS</h2>
                                <button className="openpopover" onClick={handleNewLedgPop}>+</button>
                            </div>
                            <table>
                                <tr>
                                    <th>Amount</th>
                                    <th>Date Posted</th>
                                    <th>Ledger Item</th>
                                    <th>Property</th>
                                </tr>
                                <tr onClick={handleLedgPop}>
                                    <td>$250.00</td>
                                    <td>January 1, 2024</td>
                                    <td>January Rent</td>
                                    <td>104 Catherine Lane</td>
                                </tr>
                                <tr onClick={handleLedgPop}>
                                    <td>$250.00</td>
                                    <td>December 1, 2023</td>
                                    <td>December Rent</td>
                                    <td>104 Catherine Lane</td>
                                </tr>
                                <tr onClick={handleLedgPop}>
                                    <td>$250.00</td>
                                    <td>December 1, 2023</td>
                                    <td>December Rent</td>
                                    <td>104 Catherine Lane</td>
                                </tr>
                                <tr onClick={handleLedgPop}>
                                    <td>$250.00</td>
                                    <td>December 1, 2023</td>
                                    <td>December Rent</td>
                                    <td>104 Catherine Lane</td>
                                </tr>
                                <tr onClick={handleLedgPop}>
                                    <td>$250.00</td>
                                    <td>December 1, 2023</td>
                                    <td>December Rent</td>
                                    <td>104 Catherine Lane</td>
                                </tr>
                            </table>
                            <div className={isNewLedgPop ? "proppopoverOpen" :"proppopoverClosed"} >
                                <div className="closepopover">
                                    <button onClick={handleNewLedgPop}>X</button>
                                </div>
                                <div className="newLedger">
                                    <h2>Enter New Ledger Details</h2>
                                    <form action="submit" onSubmit="">
                                        <div>
                                            <select id="ledgerType">
                                                <option value="+">Account Charge</option>
                                                <option value="-">Account Credit</option>
                                            </select>
                                        </div>

                                        <label>Amount</label>
                                        <input id="paymentAmount" type="text"  />
                                        
                                        <label>Description</label>
                                        <textarea id="ledger description" type="text" rows="3"/>
                                        
                                        <label>Associated Property</label>
                                        <select name="" id="" multiple>
                                            <option value="User Account 1">Property 1</option>
                                            <option value="User Account 2">Property 2</option>
                                        </select>

                                        <button type="submit">Submit Payment</button>
                                    </form>
                                    <div className>
                                        <h1>Response Message</h1>
                                    </div>
                                </div>
                            </div>
                            <div className={isLedgPop ? "recordOpen" :"offscreen"} >
                                <div className="closeRecord">
                                    <button onClick={handleLedgPop}>X</button>
                                </div>
                                <div>
                                    <p>Type: Credit Card</p>
                                    <p>Transaction ID: ###-####</p>
                                    <p>Card: **** **** **** **** 1234</p>
                                    <p>Date Time Posted: 1/2/2024 13:32 EST</p>
                                    <p>Status: Approved</p>
                                </div>
                            </div> 
                        </div>
    );
}

export default DetailsLedger;
