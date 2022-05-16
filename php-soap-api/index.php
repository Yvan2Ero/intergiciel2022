<?php
// model
class Book
{
	public $name;
	public $year;
}

// create instance and set a book name
$book      = new Book();
$book->name = 'test 2';

$client = new SoapClient(
	'127.0.0.1:8500/server.php?wsdl',
	[
		'trace' => 1,
		'cache_wsdl' => WSDL_CACHE_NONE,
		"exception" => 0
	]
);
$resp  = $client->bookYear($book);

// afficher la reponse
echo '<pre>';
print_r($resp);
echo '</pre>';
