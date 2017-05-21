<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
		<h2>My Tickets</h2>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>From</th>
					<th>To</th>
					<th>Amount</th>
					<th>Currency</th>
					<th>Ticket Amount</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="airlineTicket" items="${airlineTickets}">
					<tr>
						<td>${airlineTicket.details.route.from}</td>
						<td>${airlineTicket.details.route.to}</td>
						<td>${airlineTicket.details.price.amount}</td>
						<td>${airlineTicket.details.price.currency}</td>
						<td>${airlineTicket.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
<%@ include file="common/footer.jspf"%>