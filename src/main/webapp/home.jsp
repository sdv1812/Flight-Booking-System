<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
		<h2>Gamma Airline Offers</h2>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>From</th>
					<th>To</th>
					<th>Price</th>
					<th>Currency</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="airlineOffers" items="${airlineOffers}">
					<tr>
						<td>${airlineOffers.route.from}</td>
						<td>${airlineOffers.route.to}</td>
						<td>${airlineOffers.price.amount}</td>
						<td>${airlineOffers.price.currency}</td>
						<td><c:url var="buyurl" scope="page" value="/user/buy-ticket">
                             <c:param name="from" value="${airlineOffers.route.from}"/>
                             <c:param name="to" value="${airlineOffers.route.to}"/>
                             <c:param name="amount" value="${airlineOffers.price.amount}"/>
                              <c:param name="currency" value="${airlineOffers.price.currency}"/>
                        </c:url>
                        <a href="${buyurl}">Buy Ticket</a>
                        &nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
<%@ include file="common/footer.jspf"%>